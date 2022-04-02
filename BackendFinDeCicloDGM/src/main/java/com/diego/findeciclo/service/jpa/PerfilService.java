package com.diego.findeciclo.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diego.findeciclo.model.Perfil;
import com.diego.findeciclo.repository.PerfilRepository;
import com.diego.findeciclo.service.IPerfilService;

@Service
public class PerfilService implements IPerfilService {

	@Autowired
	private PerfilRepository perfilRepo;
	
	@Override
	public Perfil guardarPerfil(Perfil perfil) {
		return perfilRepo.save(perfil);
	}

	@Override
	public List<Perfil> buscarPerfiles(Perfil perfil) {
		return perfilRepo.findAll();
	}

	@Override
	public Perfil editarPerfil(Perfil perfil) {
		return perfilRepo.save(perfil);
	}

	@Override
	public void eliminarPerfil(int id) {
		perfilRepo.deleteById(id);
	}

}
