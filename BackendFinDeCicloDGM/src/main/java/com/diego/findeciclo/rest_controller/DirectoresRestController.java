package com.diego.findeciclo.rest_controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.diego.findeciclo.model.Director;
import com.diego.findeciclo.service.IDirectorService;
import com.diego.findeciclo.specification.DirectorSpecification;

@RestController
@RequestMapping("/directores")
public class DirectoresRestController {
	
	@Autowired
	private IDirectorService directorService;
	
	//Métodos CRUD
	
	@GetMapping("/listarTodos")
	public List<Director> listarTodos() {
		
		return directorService.buscarTodos();
		
	}
	
	@GetMapping("/buscar/{id}")
	public Director buscarPorId(@PathVariable int id) {
		
		return directorService.buscarPorId(id);
		
	}
	
	// Método que filtra por los campos que reciba
	@GetMapping("/filtrar")
	public List<Director> filtrar(@RequestParam(required = false) String nombre, @RequestParam(required = false) String apellido, @RequestParam(required = false) String pais) {
		
		Specification<Director> spec = construirSpec(nombre, apellido, pais);
		
		List<Director> directores = directorService.filtrar(spec);
		
		return directores;
		
	}
	
	
	// Métodos privados
	private Specification<Director> construirSpec(String nombre, String apellido, String pais) {
		
		// Seteamos el objeto spec con la cláusula where a null para que de forma predeterminada haga un findAll normal
        Specification<Director> spec = Specification.where(null);

        // Si el nombre no es nulo añadimos la busqueda por nombre
        if(nombre != null) {
            spec = spec.and(DirectorSpecification.nombre(nombre));
        }

        // Si el apellido no es nulo añadimos la búsqueda por apellido
        if(apellido != null) {
            spec = spec.and(DirectorSpecification.apellido(apellido));
        }

        // Si el país no es nulo añadimos la búsqueda por país
        if(pais != null) {
            spec = spec.and(DirectorSpecification.pais(pais));
        }
        
		return spec;
	}
}
