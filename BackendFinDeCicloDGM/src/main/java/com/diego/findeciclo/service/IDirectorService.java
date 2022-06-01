package com.diego.findeciclo.service;

import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import com.diego.findeciclo.model.Director;

public interface IDirectorService {
	
	Director guardarDirector(Director dire);
	List<Director> buscarTodos();
	Director buscarPorId(int id);
	Director editarDirector(Director dire);
	void eliminarDirector(int id);
	
	List<Director> filtrar(Specification<Director> spec);
	
}
