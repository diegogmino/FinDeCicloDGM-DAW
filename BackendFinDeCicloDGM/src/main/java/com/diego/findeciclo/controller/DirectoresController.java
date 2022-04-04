package com.diego.findeciclo.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diego.findeciclo.model.Director;
import com.diego.findeciclo.service.IDirectorService;

@RestController
@RequestMapping("/directores")
public class DirectoresController {
	
	@Autowired
	private IDirectorService directorService;
	
	/*
	 * Métodos CRUD
	 * */
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
		
		File foto = new File(direBuscado.getFoto());
		foto.delete();
		
		directorService.eliminarDirector(id);
	}
	
	// Descargar y guardar foto del director
	private String descargarImagen(String url, String nombre, String apellido) {
		
		String ruta = "src\\main\\resources\\fotos_directores\\"+apellido+"_"+nombre+".png";
		
		try {
			InputStream in = new URL(url).openStream();
			Files.copy(in, Paths.get(ruta), StandardCopyOption.REPLACE_EXISTING);
			return ruta;
		} catch (IOException e) {
			return null;
		}
		
	}
}
