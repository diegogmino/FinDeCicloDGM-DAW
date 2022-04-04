package com.diego.findeciclo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.diego.findeciclo.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Query("SELECT u FROM Usuario u WHERE u.email LIKE ?1 AND u.contrasena LIKE ?2")
	public Usuario buscarUsuario(String email, String contrasena);
	
}
