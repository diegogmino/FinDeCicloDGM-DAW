package com.diego.findeciclo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diego.findeciclo.dto.UsuarioDTO;
import com.diego.findeciclo.mapper.UsuarioMapper;
import com.diego.findeciclo.model.Usuario;
import com.diego.findeciclo.service.IUsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private UsuarioMapper usuarioMapper;
	
	
	public Usuario registro(@RequestBody Usuario usuario) {
		return null;
	}
	
	@GetMapping("/buscar/{id}")
	public UsuarioDTO listarPorId(@PathVariable int id) {

		Usuario usuario = usuarioService.buscarPorId(id);
		return usuarioMapper.toUsuarioDTO(usuario);
		
	}
	
	@GetMapping("/listarTodos")
	public List<UsuarioDTO> listarTodos() {
		
		List<Usuario> usuarios = usuarioService.buscarTodos();
		return usuarioMapper.toListUsuarioDTO(usuarios);
		
	}
	
	public Usuario actualizar(@RequestBody Usuario usuario, @PathVariable int id) {
		return null;
	}
	
	public void borrar(@PathVariable int id) {
		
	}
	
}
