package com.diego.findeciclo.service;

import java.util.List;

import com.diego.findeciclo.model.Director;

public interface IDirectorService {
	
	Director guardarDirector(Director dire);
	List<Director> buscarTodos();
	Director buscarPorId(int id);
	Director editarDirector(Director dire);
	void eliminarDirector(int id);
	
	List<Director> buscarPorPais(String pais);
	List<Director> buscarPorNombre(String nombre);
	List<Director> buscarPorApellido(String apellido);
	
}
