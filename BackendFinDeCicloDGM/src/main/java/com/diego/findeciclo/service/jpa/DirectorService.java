package com.diego.findeciclo.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.diego.findeciclo.model.Director;
import com.diego.findeciclo.repository.DirectorRepository;
import com.diego.findeciclo.service.IDirectorService;
import com.diego.findeciclo.specification.DirectorSpecification;

@Service
public class DirectorService implements IDirectorService {

	@Autowired
	private DirectorRepository direRepo;
	
	@Override
	public Director guardarDirector(Director dire) {
		return direRepo.save(dire);
	}

	@Override
	public List<Director> buscarTodos() {
		return direRepo.findAll();
	}

	@Override
	public Director buscarPorId(int id) {
		return direRepo.findById(id).get();
	}

	@Override
	public Director editarDirector(Director dire) {
		return direRepo.save(dire);
	}

	@Override
	public void eliminarDirector(int id) {
		direRepo.deleteById(id);
	}

	@Override
	public List<Director> filtrar(Specification<Director> spec) {
		return direRepo.findAll(spec);
	}

}
