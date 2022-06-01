package com.diego.findeciclo.rest_controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.diego.findeciclo.dto.CreateUsuarioDTO;
import com.diego.findeciclo.dto.MailContacto;
import com.diego.findeciclo.dto.PeliculaPedidoDTO;
import com.diego.findeciclo.dto.UpdateUsuarioDTO;
import com.diego.findeciclo.dto.UsuarioDTO;
import com.diego.findeciclo.mapper.PeliculaMapper;
import com.diego.findeciclo.mapper.UsuarioMapper;
import com.diego.findeciclo.model.Usuario;
import com.diego.findeciclo.model.MetodoPago;
import com.diego.findeciclo.model.Pedido;
import com.diego.findeciclo.model.Pelicula;
import com.diego.findeciclo.model.Perfil;
import com.diego.findeciclo.service.IEnvioMailService;
import com.diego.findeciclo.service.IPeliculaService;
import com.diego.findeciclo.service.IPerfilService;
import com.diego.findeciclo.service.IUsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("http://localhost:3000")
public class UsuariosRestController {

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IPeliculaService peliculaService;

	@Autowired
	private IPerfilService perfilService;

	@Autowired
	private IEnvioMailService mailService;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// Métodos CRUD

	@PostMapping("/guardar")
	public ResponseEntity<UsuarioDTO> registro(@RequestBody CreateUsuarioDTO createUsuarioDTO) {

		if(usuarioService.buscarEmail(createUsuarioDTO.getEmail())) {
			return new ResponseEntity<UsuarioDTO>(HttpStatus.BAD_REQUEST);
		}

		Usuario usuario = UsuarioMapper.INSTANCE.toUsuario(createUsuarioDTO);
		
		java.sql.Date fechaRegistro = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		usuario.setFechaRegistro(fechaRegistro);
		usuario.setEstatus(1);

		usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));

		Perfil perfil = perfilService.buscarPerfil(2);
		usuario.setPerfil(perfil);

		String mensaje = "¡Bienvenid@ a la familia, " + usuario.getNombre() + "!" + System.lineSeparator() + System.lineSeparator() +
		"Te has registrado correctamente en DCine. Ahora puedes empezar a comprar el mejor cine al mejor precio, ¡nos vemos por los mundos fílmicos!" + System.lineSeparator() + System.lineSeparator() +
		"El equipo de DCine :)";

		mailService.sendEmail(usuario.getEmail(), "¡Registro completado!", mensaje);

		return new ResponseEntity<UsuarioDTO>(UsuarioMapper.INSTANCE.toUsuarioDTO(usuarioService.guardarUsuario(usuario)), HttpStatus.OK);

	}

	@PostMapping("/mailContacto")
	public ResponseEntity<Boolean> enviarMailContacto(@RequestBody MailContacto mail) {

		String mensaje = "-------- Datos del usuario --------" + System.lineSeparator() +
		"Nombre:" + mail.getNombre() + System.lineSeparator() +
		"Apellidos: " + mail.getApellidos() + System.lineSeparator() +
		"Correo: " + mail.getMail() + System.lineSeparator() +
		"País del usuario: " + mail.getPais() + System.lineSeparator() +
		"-------- Fin de datos del usuario --------" + System.lineSeparator() + System.lineSeparator() +
		"Mensaje: "+ System.lineSeparator() + System.lineSeparator() + mail.getMensaje();

		mailService.sendEmail("contacto.dcine@gmail.com", "Mensaje de contacto de " + mail.getMail(), mensaje);

		return new ResponseEntity<Boolean>(true, HttpStatus.OK);

	}

	@GetMapping("/login/{email}/{contrasena}")
	public ResponseEntity<UsuarioDTO> login(@PathVariable String email, @PathVariable String contrasena) {

		UsuarioDTO usuario = usuarioService.usuarioEncontrado(email, contrasena);

		if(usuario == null) {
			return new ResponseEntity<UsuarioDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<UsuarioDTO>(usuario, HttpStatus.OK);

	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<UsuarioDTO> listarPorId(@PathVariable int id) {

		Usuario usuario = usuarioService.buscarPorId(id);

		if(usuario == null) {
			return new ResponseEntity<UsuarioDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<UsuarioDTO>(UsuarioMapper.INSTANCE.toUsuarioDTO(usuario), HttpStatus.OK);
		
	}

	@GetMapping("/buscarPedidos/{id}")
	public ResponseEntity<List<Pedido>> listarPedidosPorUsuario(@PathVariable int id) {

		Usuario usuario = usuarioService.buscarPorId(id);

		if(usuario == null) {
			return new ResponseEntity<List<Pedido>>(HttpStatus.NOT_FOUND);
		}

		List<Pedido> pedidosUsuario = usuario.getPedidos();

		ArrayList<Pedido> pedidos = new ArrayList<Pedido>();

        for (Pedido pedido : pedidosUsuario) {
            if (!pedidos.contains(pedido)) {
                pedidos.add(pedido);
            }
        }

		return new ResponseEntity<List<Pedido>>(pedidos, HttpStatus.OK);
		
	}
	
	@GetMapping("/listarTodos")
	public List<UsuarioDTO> listarTodos() {
		
		List<Usuario> usuarios = usuarioService.buscarTodos();
		return UsuarioMapper.INSTANCE.toListUsuarioDTO(usuarios);
		
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<UsuarioDTO> actualizar(@RequestBody UpdateUsuarioDTO updateUsuarioDTO, @PathVariable int id) {

		Usuario usuarioBBDD = usuarioService.buscarPorId(id);

		if(usuarioBBDD == null) {
			return new ResponseEntity<UsuarioDTO>(HttpStatus.NOT_FOUND);
		}

		usuarioBBDD.setNombre(updateUsuarioDTO.getNombre());
		usuarioBBDD.setApellido(updateUsuarioDTO.getApellido());
		usuarioBBDD.setEmail(updateUsuarioDTO.getEmail());
		usuarioBBDD.setContrasena(updateUsuarioDTO.getContrasena());
		usuarioBBDD.setTelefono(updateUsuarioDTO.getTelefono());
		usuarioBBDD.setDireccion(updateUsuarioDTO.getDireccion());
		usuarioBBDD.setPais(updateUsuarioDTO.getPais());

		return new ResponseEntity<UsuarioDTO>(UsuarioMapper.INSTANCE.toUsuarioDTO(usuarioService.guardarUsuario(usuarioBBDD)), HttpStatus.OK);

	}
	
	@DeleteMapping("/borrar/{id}")
	public ResponseEntity<Void> borrar(@PathVariable int id) {
		
		if(usuarioService.buscarPorId(id) == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		usuarioService.eliminarUsuario(id);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	// Operaciones de los usuarios

	@PostMapping("/nuevoPedido/{id}")
	public ResponseEntity<Pedido> anhadirPedidoUsuario(@RequestBody List<PeliculaPedidoDTO> peliculas, @PathVariable int id) {

		Usuario usuario = usuarioService.buscarPorId(id);

		if(usuario == null) {
			return new ResponseEntity<Pedido>(new Pedido(), HttpStatus.NOT_FOUND);
		}

		Pedido pedido = new Pedido();
		pedido.setDireccionEnvio(usuario.getDireccion());
		pedido.setEntregado(false);
		pedido.setFechaPedido(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		pedido.setPedidosUsuario(usuario);
		pedido.setPeliculas(PeliculaMapper.INSTANCE.toListPelicula(peliculas));

		for(PeliculaPedidoDTO pelicula : peliculas) {

			Pelicula peli = peliculaService.buscarPorId(pelicula.getId());
			int unidades = peli.getUnidades();
			unidades = unidades - pelicula.getQty();
			peli.setUnidades(unidades);
			peliculaService.guardarPeli(peli);

		}
		
		float precioTotal = 0;

		for(PeliculaPedidoDTO pelicula : peliculas) {
			precioTotal = precioTotal + (pelicula.getPrecio().floatValue() * pelicula.getQty());
		}

		if(precioTotal < 40) {
			precioTotal = (float) (precioTotal + 3.99);
		}

		pedido.setPrecioTotal(new BigDecimal(precioTotal));

		List<Pedido> pedidos = usuario.getPedidos();
		pedidos.add(pedido);
		usuario.setPedidos(pedidos);

		usuario = usuarioService.guardarUsuario(usuario);

		pedido = usuario.getPedidos().get(usuario.getPedidos().size() - 1);

		return new ResponseEntity<Pedido>(pedido, HttpStatus.OK);

	}

	@PostMapping("/nuevoMetodoPago/{id}")
	public ResponseEntity<Void> anhadirMetodoPago(@RequestBody MetodoPago metodoPago, int id) {

		Usuario usuario = usuarioService.buscarPorId(id);

		if(usuario == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		List<MetodoPago> metodosPago = usuario.getMetodoDePago();
		metodosPago.add(metodoPago);
		usuario.setMetodoDePago(metodosPago);

		usuarioService.guardarUsuario(usuario);

		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@DeleteMapping("/eliminarMetodoPago/{id}")
	public ResponseEntity<Void> eliminarMetodoPago(@RequestBody MetodoPago metodoPago, int id) {

		Usuario usuario = usuarioService.buscarPorId(id);

		if(usuario == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		List<MetodoPago> metodosPago = usuario.getMetodoDePago();
		List<MetodoPago> nuevosMetodosPago = new ArrayList<MetodoPago>();
		
		for(MetodoPago metodo : metodosPago) {
			if(!metodo.equals(metodoPago)) {
				nuevosMetodosPago.add(metodo);
			}
		}

		usuario.setMetodoDePago(nuevosMetodosPago);

		usuarioService.guardarUsuario(usuario);

		return new ResponseEntity<Void>(HttpStatus.OK);

	}
	
}
