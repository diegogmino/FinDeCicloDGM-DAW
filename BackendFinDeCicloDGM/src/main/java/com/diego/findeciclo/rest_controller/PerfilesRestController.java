package com.diego.findeciclo.rest_controller;

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
import com.diego.findeciclo.model.Perfil;
import com.diego.findeciclo.service.IPerfilService;

@RestController
@RequestMapping("/perfiles")
public class PerfilesRestController {

	@Autowired
	private IPerfilService perfilService;
	
	// Métodos CRUD
	
	@PostMapping("/guardar")
	public Perfil guardarPerfil(@RequestBody Perfil perfil) {
		return perfilService.guardarPerfil(perfil);
	}
	
	@GetMapping("/listarTodos")
	public List<Perfil> buscarTodos() {
		return perfilService.buscarTodos();
	}
	
	@GetMapping("/buscar/{id}")
	public Perfil buscarPorId(@PathVariable int id) {
		return perfilService.buscarPerfil(id);
	}
	
	@PutMapping("/actualizar/{id}")
	public Perfil actualizarPerfil(@PathVariable int id, @RequestBody Perfil perfil) {
		
		Perfil perfilEncontrado = perfilService.buscarPerfil(id);
		perfilEncontrado.setPerfil(perfil.getPerfil());
		return perfilService.guardarPerfil(perfilEncontrado);
		
	}
	
	@DeleteMapping("/borrar/{id}")
	public void eliminarPerfil(@PathVariable int id) {
		perfilService.eliminarPerfil(id);
	}
	
}
