package com.diego.findeciclo.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.diego.findeciclo.model.MetodoPago;

public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Integer> {
	
	@Query("SELECT m FROM MetodoPago m WHERE m.fechaCaducidad LIKE ?1")
	public List<MetodoPago> filtrarFechaCaducidad(Date fechaCaducidad);

}
