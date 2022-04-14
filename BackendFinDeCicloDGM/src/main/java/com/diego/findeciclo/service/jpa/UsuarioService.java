package com.diego.findeciclo.service.jpa;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.diego.findeciclo.model.Usuario;
import com.diego.findeciclo.repository.UsuarioRepository;
import com.diego.findeciclo.service.IUsuarioService;

@Service
public class UsuarioService implements IUsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Override
	public Usuario guardarUsuario(Usuario usuario) {
		return usuarioRepo.save(usuario);
	}

	@Override
	public List<Usuario> buscarTodos() {
		return usuarioRepo.findAll();
	}

	@Override
	public Usuario buscarPorId(int id) {
		return usuarioRepo.findById(id).get();
	}

	@Override
	public Boolean usuarioEncontrado(String email, String contrasena) {
		
		Usuario usuario = usuarioRepo.buscarUsuario(email, contrasena);
		
		if(usuario.getNombre() != null) {
			return true;
		} else {
			return false;
		}
		
	}

	@Override
	public Usuario actualizarUsuario(Usuario usuario) {
		return usuarioRepo.save(usuario);
	}

	@Override
	public void eliminarUsuario(int id) {
		usuarioRepo.deleteById(id);
	}

	@Override
	public Boolean buscarEmail(String email) {

		if(usuarioRepo.buscarEmail(email) != null) {
			return false;
		} else {
			return true;
		}
		
	}
	
}
