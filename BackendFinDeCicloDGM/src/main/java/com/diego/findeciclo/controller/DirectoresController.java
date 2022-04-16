package com.diego.findeciclo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/admin/directores")
public class DirectoresController {
    
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String mostrarPeliculas() {
        return "directores/listadoDirectores";
    }

}
