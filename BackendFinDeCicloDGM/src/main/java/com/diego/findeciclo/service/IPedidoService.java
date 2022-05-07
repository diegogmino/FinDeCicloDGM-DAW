package com.diego.findeciclo.service;

import java.util.List;
import com.diego.findeciclo.model.Pedido;
import org.springframework.data.jpa.domain.Specification;

public interface IPedidoService {

	Pedido guardarPedido(Pedido pedido);
	List<Pedido> buscarTodos();
	Pedido buscarPorId(int id);
	void eliminarPedido(int id);
	
	List<Pedido> filtrar(Specification<Pedido> spec);
	
}
