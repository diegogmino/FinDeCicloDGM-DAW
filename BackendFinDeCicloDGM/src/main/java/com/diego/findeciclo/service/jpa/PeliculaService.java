package com.diego.findeciclo.service.jpa;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.diego.findeciclo.model.Pelicula;
import com.diego.findeciclo.repository.PeliculaRepository;
import com.diego.findeciclo.service.IPeliculaService;

@Service
public class PeliculaService implements IPeliculaService {

	@Autowired
	private PeliculaRepository peliRepo;

	@Override
	public Pelicula guardarPeli(Pelicula pelicula) {
		return peliRepo.save(pelicula);
	}

	@Override
	public List<Pelicula> buscarTodas() {
		return peliRepo.findAll();
	}

	@Override
	public Pelicula buscarPorId(int id) {
		return peliRepo.findById(id).get();
	}

	@Override
	public Pelicula editarPeli(Pelicula peli) {
		return peliRepo.save(peli);
	}

	@Override
	public void eliminarPeli(int id) {
		peliRepo.deleteById(id);
	}


	@Override
	public List<Pelicula> filtrarDestacada() {
		return peliRepo.filtrarDestacada();
	}

	@Override
	public List<Pelicula> filtrar(Specification<Pelicula> spec) {
		return peliRepo.findAll(spec);
	}
	
	

}
