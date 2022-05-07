package com.diego.findeciclo.rest_controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diego.findeciclo.model.Pedido;
import com.diego.findeciclo.model.Pelicula;
import com.diego.findeciclo.model.Usuario;
import com.diego.findeciclo.service.IPedidoService;
import com.diego.findeciclo.service.IUsuarioService;

@RestController
@RequestMapping("/pedidos")
public class PedidosRestController {

	@Autowired
	private IPedidoService pedidoService;
	
	@Autowired
	IUsuarioService usuarioService;
	
	// Métodos CRUD
	
	@PostMapping("/guardar/{id}")
	public Pedido guardarPedido(@RequestBody Pedido pedido, @PathVariable int id) {
		
		Usuario usu = usuarioService.buscarPorId(id);
		pedido.setPedidosUsuario(usu);
		
		pedido.setDireccionEnvio(usu.getDireccion());
		pedido.setEntregado(false);
		
		long now = System.currentTimeMillis();
		Date date = new Date(now);
		pedido.setFechaPedido(new java.sql.Date(date.getTime()));
		
		BigDecimal precio = new BigDecimal(0);
		
		for(Pelicula peli : pedido.getPeliculas()) {
			precio = precio.add(peli.getPrecio());
		}
		
		pedido.setPrecioTotal(precio);
		
		
		
		// Hacer que el precio total sea de verdad
		
		return pedidoService.guardarPedido(pedido);
	}
	
	@GetMapping("/listarTodos")
	public List<Pedido> listarTodos() {
		return pedidoService.buscarTodos();
	}
	
	@GetMapping("/buscar/{id}")
	public Pedido buscarPedido(@PathVariable int id) {
		return pedidoService.buscarPorId(id);
	}
	
	@DeleteMapping("/borrar/{id}")
	public void borrarPedido(@PathVariable int id) {
		pedidoService.eliminarPedido(id);
	}
}
