package com.diego.findeciclo.service;

import java.sql.Date;
import java.util.List;

import com.diego.findeciclo.model.Pedido;

public interface IPedidoService {

	Pedido guardarPedido(Pedido pedido);
	List<Pedido> buscarTodos();
	Pedido actualizarPedido(Pedido pedido);
	void eliminarPedido(int id);
	
	List<Pedido> filtrarEntregados(List<Pedido> pedidosUsuario);
	List<Pedido> filtrarFechas(Date minFecha, Date maxFecha);
	
}
