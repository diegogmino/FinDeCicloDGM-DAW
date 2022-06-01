package com.diego.findeciclo.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	@Autowired
	private PasswordEncoder passwordEncoder;
    
    @GetMapping("/login")
	public String mostrarLogin() {
		return "formLogin";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request, null, null);
		return "redirect:/login";
	}
	
	@GetMapping("/")
	public String peliculasUsuario() {
		return "redirect:/admin/peliculas/index";
	}

	@GetMapping("/bcrypt/{texto}")
	@ResponseBody 
	public String encriptar(@PathVariable("texto") String texto) {
		return texto + " Encriptado en BCrypt: " + passwordEncoder.encode(texto);
	}

}
