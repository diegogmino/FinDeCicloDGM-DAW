package com.diego.findeciclo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.diego.findeciclo.model.Director;
import com.diego.findeciclo.service.IDirectorService;
import com.diego.findeciclo.specification.DirectorSpecification;

@RestController
@RequestMapping("/directores")
public class DirectoresController {
	
	@Autowired
	private IDirectorService directorService;
	
	//Métodos CRUD
	
	@PostMapping("/guardar")
	public Director guardarDirector(@RequestBody Director dire) {
		
		String nuevaFoto = descargarImagen(dire.getFoto(), dire.getNombre(), dire.getApellido());
		dire.setFoto(nuevaFoto);
		return directorService.guardarDirector(dire);
		
	}
	
	@GetMapping("/listarTodos")
	public List<Director> listarTodos() {
		
		return directorService.buscarTodos();
		
	}
	
	@GetMapping("/buscar/{id}")
	public Director buscarPorId(@PathVariable int id) {
		
		return directorService.buscarPorId(id);
		
	}
	
	@PutMapping("/actualizar/{id}")
	public Director editarDirector(@PathVariable int id, @RequestBody Director dire) {
		
		Director direBuscado = directorService.buscarPorId(id);
		direBuscado.setNombre(dire.getNombre());
		direBuscado.setApellido(dire.getApellido());
		direBuscado.setPais(dire.getPais());
		
		
		if(dire.getFoto().contains("src\\main\\resources\\fotos_directores")) {
			direBuscado.setFoto(dire.getFoto());
		} else {
			
			String nuevaFoto = descargarImagen(dire.getFoto(), dire.getNombre(), dire.getApellido());
			dire.setFoto(nuevaFoto);
			
		}
		
		return directorService.guardarDirector(direBuscado);
	}
	
	@DeleteMapping("/borrar/{id}")
	public void borrarDirector(@PathVariable int id) {
		
		Director direBuscado = directorService.buscarPorId(id);
		
		// Eliminar la imagen asociada al director
        try {
            Files.delete(Paths.get(direBuscado.getFoto()));
        } catch (IOException e) {
            e.printStackTrace();
        }
		
        // Eliminar el propio registro del director
		directorService.eliminarDirector(id);
	}
	
	
	// Método que filtra por los campos que reciba
	@GetMapping("/filtrar")
	public List<Director> filtrar(@RequestParam(required = false) String nombre, @RequestParam(required = false) String apellido, @RequestParam(required = false) String pais) {
		
		Specification<Director> spec = construirSpec(nombre, apellido, pais);
		
		List<Director> directores = directorService.filtrar(spec);
		
		return directores;
		
	}
	
	
	// Métodos privados
	
	private String descargarImagen(String url, String nombre, String apellido) {
		
		String ruta = "src\\main\\resources\\fotos_directores\\"+apellido+"_"+nombre+".jpeg";
		
		try {
			InputStream in = new URL(url).openStream();
			Files.copy(in, Paths.get(ruta), StandardCopyOption.REPLACE_EXISTING);
			return ruta;
		} catch (IOException e) {
			return null;
		}
		
	}
	
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
