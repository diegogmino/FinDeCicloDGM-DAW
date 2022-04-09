package com.diego.findeciclo.service.jpa;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diego.findeciclo.model.Pedido;
import com.diego.findeciclo.model.Usuario;
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
	public Pedido buscarPorId(int id) {
		return pedidoRepo.findById(id).get();
	}

	@Override
	public void eliminarPedido(int id) {
		pedidoRepo.deleteById(id);
	}

	@Override
	public List<Pedido> filtrarEntregados(Usuario usuario) {
		return pedidoRepo.filtrarEntregadosPorUsuario(usuario);
	}

	@Override
	public List<Pedido> filtrarFechas(Date minFecha, Date maxFecha) {
		return pedidoRepo.filtrarFecha(minFecha, maxFecha);
	}

	@Override
	public List<Pedido> filtrarPorUsuario(Usuario usuario) {
		return pedidoRepo.filtrarUsuario(usuario);
	}
	
}
