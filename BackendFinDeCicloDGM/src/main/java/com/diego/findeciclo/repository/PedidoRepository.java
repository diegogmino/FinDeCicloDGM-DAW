package com.diego.findeciclo.repository;

import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.diego.findeciclo.model.Pedido;
import com.diego.findeciclo.model.Usuario;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	
	@Query("SELECT p FROM Pedido p WHERE p.fechaPedido BETWEEN ?1 AND ?2")
	public List<Pedido> filtrarFecha(Date fechaMin, Date fechaMax);
	
	@Query("SELECT p FROM Pedido p WHERE p.pedidosUsuario = ?1")
	public List<Pedido> filtrarUsuario(Usuario usuario);
	
	@Query("SELECT p FROM Pedido p WHERE p.pedidosUsuario = ?1 AND p.entregado = TRUE")
	public List<Pedido> filtrarEntregadosPorUsuario(Usuario usuario);

}
