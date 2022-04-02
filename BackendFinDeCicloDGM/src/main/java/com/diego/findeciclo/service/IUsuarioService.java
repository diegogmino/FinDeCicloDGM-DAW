package com.diego.findeciclo.service;

import java.util.List;

import com.diego.findeciclo.model.Usuario;

public interface IUsuarioService {
	
	Usuario guardarUsuario(Usuario usuario);
	List<Usuario> buscarTodos();
	Usuario buscarPorId(int id);
	Boolean usuarioEncontrado(String email, String contrasena);
	Usuario actualizarUsuario(Usuario usuario);
	void eliminarUsuario(int id);

}
