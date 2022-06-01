package com.diego.findeciclo.service;

import java.util.List;
import com.diego.findeciclo.model.Perfil;

public interface IPerfilService {

	Perfil guardarPerfil(Perfil perfil);
	List<Perfil> buscarTodos();
	Perfil buscarPerfil(int id);
	Perfil editarPerfil(Perfil perfil);
	void eliminarPerfil(int id);
	
}
