package com.diego.findeciclo.service;

import java.sql.Date;
import java.util.List;

import com.diego.findeciclo.model.MetodoPago;

public interface IMetodoPagoService {

	MetodoPago guardarMetodo(MetodoPago metodoPago);
	List<MetodoPago> buscarTodos();
	MetodoPago buscarPorUsuario(int idUsuario);
	MetodoPago editarMetodo(MetodoPago metodoPago);
	void eliminarMetodo(int id);
	
	List<MetodoPago> buscarPorFechaCaducidad(Date fechaCaducidad);
	
}