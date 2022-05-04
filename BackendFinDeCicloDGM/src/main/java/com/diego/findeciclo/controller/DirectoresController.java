package com.diego.findeciclo.controller;

import java.util.List;

import com.diego.findeciclo.model.Director;
import com.diego.findeciclo.service.IDirectorService;
import com.diego.findeciclo.specification.DirectorSpecification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/admin/directores")
public class DirectoresController {
    
    @Autowired
    private IDirectorService directorService;


    // MÉTODOS GET
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String mostrarDirectoresString(Model model) {

        List<Director> directores = directorService.buscarTodos();
        model.addAttribute("directores", directores);
        return "directores/listadoDirectores";

    }

    // Filtro
	@RequestMapping(value = "/filtrar", method = RequestMethod.GET)
	public String filtrar(@RequestParam(required = false) String nombre, @RequestParam(required = false) String apellido, @RequestParam(required = false) String pais, Model model) {

		Specification<Director> spec = construirSpecification(nombre, apellido, pais);
        List<Director> directores = directorService.filtrar(spec);

        model.addAttribute("directores", directores);


		return "directores/listadoDirectores";
		
	}

    private Specification<Director> construirSpecification(String nombre, String apellido, String pais) {
		
		// Seteamos el objeto spec con la cláusula where a null para que de forma predeterminada haga un findAll normal
        Specification<Director> spec = Specification.where(null);

        if(nombre != "") {
            spec = spec.and(DirectorSpecification.nombre(nombre));
        }

        if(apellido != "") {
            spec = spec.and(DirectorSpecification.apellido(apellido));
        }
        
        if(pais != "") {
            spec = spec.and(DirectorSpecification.pais(pais));
        }
        
		return spec;
	}

}
