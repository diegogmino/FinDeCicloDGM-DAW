package com.diego.findeciclo.service;

import java.sql.Date;
import java.util.List;

import com.diego.findeciclo.model.Pedido;
import com.diego.findeciclo.model.Usuario;

public interface IPedidoService {

	Pedido guardarPedido(Pedido pedido);
	List<Pedido> buscarTodos();
	Pedido buscarPorId(int id);
	void eliminarPedido(int id);
	
	List<Pedido> filtrarEntregados(Usuario usuario);
	List<Pedido> filtrarFechas(Date minFecha, Date maxFecha);
	List<Pedido> filtrarPorUsuario(Usuario usuario);
	
}
