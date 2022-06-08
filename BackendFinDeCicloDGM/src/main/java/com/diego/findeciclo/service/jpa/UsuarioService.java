package com.diego.findeciclo.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.diego.findeciclo.dto.UsuarioDTO;
import com.diego.findeciclo.mapper.UsuarioMapper;
import com.diego.findeciclo.model.Usuario;
import com.diego.findeciclo.repository.UsuarioRepository;
import com.diego.findeciclo.service.IUsuarioService;

@Service
public class UsuarioService implements IUsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

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
	public UsuarioDTO usuarioEncontrado(String email, String contrasena) {

		Optional<Usuario> usuario = usuarioRepo.buscarEmail(email);

		if (passwordEncoder.matches(contrasena, usuario.get().getContrasena())) {
			return UsuarioMapper.INSTANCE.toUsuarioDTO(usuario.get());
		} else {
			return null;
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

		Optional<Usuario> usuario = usuarioRepo.buscarEmail(email);

		if (usuario.isEmpty()) {
			return false;
		} else {
			return true;
		}

	}

}
