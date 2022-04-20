package com.diego.findeciclo.controller;

import java.util.List;

import com.diego.findeciclo.model.Pelicula;
import com.diego.findeciclo.service.IPeliculaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/admin/peliculas")
public class PeliculasController {
    
    @Autowired
	private IPeliculaService peliculaService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String mostrarPeliculas(Model model) {

        List<Pelicula> peliculas = peliculaService.buscarTodas();
        model.addAttribute("peliculas", peliculas);
        return "peliculas/listadoPeliculas";
    }

}
