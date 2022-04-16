package com.diego.findeciclo.controller;

import com.diego.findeciclo.service.IPeliculaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/admin/peliculas")
public class PeliculasController {
    
    @Autowired
	private IPeliculaService peliculaService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String mostrarPeliculas() {
        return "peliculas/listadoPeliculas";
    }

}
