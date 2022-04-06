package com.diego.findeciclo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.diego.findeciclo.model.MetodoPago;
import com.diego.findeciclo.service.IMetodoPagoService;

@RestController
@RequestMapping("/metodosPago")
public class MetodosPagoController {

	@Autowired
	private IMetodoPagoService metodoPagoService;
	
	@PostMapping("/guardar")
	public MetodoPago guardar(@RequestBody MetodoPago metodoPago) {
		return metodoPagoService.guardarMetodo(metodoPago);
	}
	
	@PutMapping("/actualizar/{id}")
	public MetodoPago actualizar(@RequestBody MetodoPago metodoPago, @PathVariable int id) {
		
		MetodoPago metodoPagoEncontrado = metodoPagoService.buscarPorId(id);
		
		metodoPagoEncontrado.setNumeroTarjeta(metodoPago.getNumeroTarjeta());
		metodoPagoEncontrado.setFechaCaducidad(metodoPago.getFechaCaducidad());
		metodoPagoEncontrado.setCvv(metodoPago.getCvv());
		
		return metodoPagoService.guardarMetodo(metodoPagoEncontrado);
	}
	
	@DeleteMapping("/borrar/{id}")
	public void eliminar(@PathVariable int id) {
		metodoPagoService.eliminarMetodo(id);
	}
	
}
