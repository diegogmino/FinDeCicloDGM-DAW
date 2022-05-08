package com.diego.findeciclo.rest_controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.diego.findeciclo.dto.CreateUsuarioDTO;
import com.diego.findeciclo.dto.UpdateUsuarioDTO;
import com.diego.findeciclo.dto.UsuarioDTO;
import com.diego.findeciclo.mapper.UsuarioMapper;
import com.diego.findeciclo.model.Usuario;
import com.diego.findeciclo.model.MetodoPago;
import com.diego.findeciclo.model.Pedido;
import com.diego.findeciclo.model.Perfil;
import com.diego.findeciclo.service.IPerfilService;
import com.diego.findeciclo.service.IUsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuariosRestController {

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IPerfilService perfilService;

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

		return new ResponseEntity<UsuarioDTO>(UsuarioMapper.INSTANCE.toUsuarioDTO(usuarioService.guardarUsuario(usuario)), HttpStatus.OK);

	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<UsuarioDTO> listarPorId(@PathVariable int id) {

		Usuario usuario = usuarioService.buscarPorId(id);

		if(usuario == null) {
			return new ResponseEntity<UsuarioDTO>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<UsuarioDTO>(UsuarioMapper.INSTANCE.toUsuarioDTO(usuario), HttpStatus.OK);
		
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
	public ResponseEntity<Void> anhadirPedidoUsuario(@RequestBody Pedido pedido, int id) {

		Usuario usuario = usuarioService.buscarPorId(id);

		if(usuario == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		List<Pedido> pedidos = usuario.getPedidos();
		pedidos.add(pedido);
		usuario.setPedidos(pedidos);

		usuarioService.guardarUsuario(usuario);

		return new ResponseEntity<Void>(HttpStatus.OK);

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
