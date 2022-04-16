package com.diego.findeciclo.controller;

import com.diego.findeciclo.service.IPeliculaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/peliculas")
public class PeliculasController {
    
    @Autowired
	private IPeliculaService peliculaService;

}
