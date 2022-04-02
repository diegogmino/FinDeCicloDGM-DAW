package com.diego.findeciclo.repository;

import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.diego.findeciclo.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	
	@Query("SELECT p FROM Pedido p WHERE p.fechaPedido BETWEEN ?1 AND ?2")
	public List<Pedido> filtrarFecha(Date fechaMin, Date fechaMax);

}
