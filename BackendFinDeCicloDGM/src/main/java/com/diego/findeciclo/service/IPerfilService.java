package com.diego.findeciclo.service;

import java.util.List;

import com.diego.findeciclo.model.Perfil;

public interface IPerfilService {

	Perfil guardarPerfil(Perfil perfil);
	List<Perfil> buscarPerfiles(Perfil perfil);
	Perfil editarPerfil(Perfil perfil);
	void eliminarPerfil(int id);
	
}
