package com.diego.findeciclo.controller;

import java.util.List;

import com.diego.findeciclo.model.Pedido;
import com.diego.findeciclo.service.IPedidoService;
import com.diego.findeciclo.specification.PedidoSpecification;

import java.math.BigDecimal;
import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/admin/pedidos")
public class PedidosController {
    
    @Autowired
    private IPedidoService pedidoService;

    // MÉTODOS GET
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String mostrarPedidos(Model model) {

        List<Pedido> pedidos = pedidoService.buscarTodos();

        model.addAttribute("pedidos", pedidos);
        return "pedidos/listadoPedidos";

    }

    @RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
    public String mostrarFormularioEditar(@PathVariable int id, Model model) {

        Pedido pedido = pedidoService.buscarPorId(id);

        model.addAttribute("pedido", pedido);
        return "pedidos/editarPedido";

    }

    @RequestMapping(value = "/detalle/{id}", method = RequestMethod.GET)
    public String detalle(@PathVariable int id, Model model) {

        Pedido pedido = pedidoService.buscarPorId(id);

        model.addAttribute("pedido", pedido);
        return "pedidos/detallePedido";

    }

    // Métodos POST
    @RequestMapping(value = "/actualizar", method = RequestMethod.POST)
    public String actualizar(@RequestParam("id") int id, @RequestParam("direccionEnvio") String direccionEnvio, @RequestParam(name="entregado") boolean entregado, Model model) {

        Pedido pedidoBBDD = pedidoService.buscarPorId(id);
        pedidoBBDD.setDireccionEnvio(direccionEnvio);
        pedidoBBDD.setEntregado(entregado);
        pedidoService.guardarPedido(pedidoBBDD);

		return "redirect:/admin/pedidos/index";

	}

    // Filtro
	@RequestMapping(value = "/filtrar", method = RequestMethod.GET)
	public String filtrar(@RequestParam(required = false) String nombre, @RequestParam(required = false) String apellido, @RequestParam(required = false) Boolean entregado, Model model) {

		Specification<Pedido> spec = construirSpecification(nombre, apellido, entregado);
        List<Pedido> pedidos = pedidoService.filtrar(spec);

        System.out.println(pedidos.toString());

        model.addAttribute("pedidos", pedidos);

		return "pedidos/listadoPedidos";
		
	}

    // Métodos privados
    private Specification<Pedido> construirSpecification(String nombre, String apellido, Boolean entregado) {
		
		// Seteamos el objeto spec con la cláusula where a null para que de forma predeterminada haga un findAll normal
        Specification<Pedido> spec = Specification.where(null);

        if(nombre != "") {
            spec = spec.and(PedidoSpecification.nombre(nombre));
        }

        if(apellido != "") {
            spec = spec.and(PedidoSpecification.apellido(apellido));
        }
        
        if(entregado != null) {
            spec = spec.and(PedidoSpecification.entregado(entregado));
        }
        
		return spec;
	}

}
