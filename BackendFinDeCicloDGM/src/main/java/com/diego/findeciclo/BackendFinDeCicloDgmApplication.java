package com.diego.findeciclo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@SpringBootApplication
public class BackendFinDeCicloDgmApplication extends WebMvcConfigurationSupport {

	public static void main(String[] args) {
		SpringApplication.run(BackendFinDeCicloDgmApplication.class, args);
	}

	 // El paquete de guía de ruta para configurar archivos de recursos estáticos aquí se importa directamente de forma predeterminada.
	 @Override
	 protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		 registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
		 super.addResourceHandlers(registry);
	 }

}
