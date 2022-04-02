package com.diego.findeciclo.service.jpa;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diego.findeciclo.model.Pedido;
import com.diego.findeciclo.repository.PedidoRepository;
import com.diego.findeciclo.service.IPedidoService;

@Service
public class PedidoService implements IPedidoService {

	@Autowired
	private PedidoRepository pedidoRepo;
	
	@Override
	public Pedido guardarPedido(Pedido pedido) {
		return pedidoRepo.save(pedido);
	}

	@Override
	public List<Pedido> buscarTodos() {
		return pedidoRepo.findAll();
	}

	@Override
	public Pedido actualizarPedido(Pedido pedido) {
		return pedidoRepo.save(pedido);
	}

	@Override
	public void eliminarPedido(int id) {
		pedidoRepo.deleteById(id);
	}

	@Override
	public List<Pedido> filtrarEntregados(List<Pedido> pedidosUsuario) {
		
		List<Pedido> pedidosEntregados = new ArrayList<Pedido>();
		
		for(Pedido pedido : pedidosUsuario) {
			if(pedido.isEntregado()) {
				pedidosEntregados.add(pedido);
			}
		}
		
		return pedidosEntregados;
		
	}

	@Override
	public List<Pedido> filtrarFechas(Date minFecha, Date maxFecha) {
		return pedidoRepo.filtrarFecha(minFecha, maxFecha);
	}

	
	
}
