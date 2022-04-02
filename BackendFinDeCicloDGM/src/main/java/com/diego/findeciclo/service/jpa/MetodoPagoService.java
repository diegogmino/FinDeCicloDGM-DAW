package com.diego.findeciclo.service.jpa;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diego.findeciclo.model.MetodoPago;
import com.diego.findeciclo.repository.MetodoPagoRepository;
import com.diego.findeciclo.service.IMetodoPagoService;

@Service
public class MetodoPagoService implements IMetodoPagoService {

	@Autowired
	private MetodoPagoRepository pagoRepo;

	@Override
	public MetodoPago guardarMetodo(MetodoPago metodoPago) {
		return pagoRepo.save(metodoPago);
	}

	@Override
	public List<MetodoPago> buscarTodos() {
		return pagoRepo.findAll();
	}

	@Override
	public MetodoPago buscarPorUsuario(int idUsuario) {
		return pagoRepo.findById(idUsuario).get();
	}

	@Override
	public MetodoPago editarMetodo(MetodoPago metodoPago) {
		return pagoRepo.save(metodoPago);
	}

	@Override
	public void eliminarMetodo(int id) {
		pagoRepo.deleteById(id);
		
	}

	@Override
	public List<MetodoPago> buscarPorFechaCaducidad(Date fechaCaducidad) {
		return pagoRepo.filtrarFechaCaducidad(fechaCaducidad);
	}
	
	
	
}
