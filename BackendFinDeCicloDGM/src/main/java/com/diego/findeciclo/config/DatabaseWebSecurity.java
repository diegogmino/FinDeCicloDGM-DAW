package com.diego.findeciclo.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class DatabaseWebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
	private DataSource dataSource;

	private static final String[] AUTH_WHITELIST = {
		"/directores/**",
		"/metodosPago/**",
		"/pedidos/**",
		"/peliculas/**",
		"/usuarios/**",
		"/bcrypt/**"
	};

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("select email, contrasena, estatus from Usuarios where email = ?")
		.authoritiesByUsernameQuery("select u.email, p.perfil from Usuarios u "
		+ "inner join Perfiles p on p.id = u.idPerfil " + "where u.email = ?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and()
			.authorizeRequests().antMatchers("/static/bootstrap/**", 
											"/static/images/**",
											"/static/css/**",
											"/static/tinymce/**").permitAll().antMatchers(AUTH_WHITELIST).permitAll()
																							.antMatchers("/admin/**").hasAnyAuthority("ADMINISTRADOR")
																							.anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
    	web.ignoring().antMatchers(HttpMethod.POST, "/usuarios/guardar").antMatchers(HttpMethod.POST, "/usuarios/nuevoPedido/**");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
