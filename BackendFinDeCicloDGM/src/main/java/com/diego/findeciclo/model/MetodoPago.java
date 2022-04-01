package com.diego.findeciclo.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "metodos_pago")
public class MetodoPago {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private int numeroTarjeta;
	private Date fechaCaducidad;
	private int cvv;
	
	@ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario metodosPagoUsuario;

	public MetodoPago() {
		super();
	}

	public MetodoPago(int numeroTarjeta, Date fechaCaducidad, int cvv, Usuario metodosPagoUsuario) {
		super();
		this.numeroTarjeta = numeroTarjeta;
		this.fechaCaducidad = fechaCaducidad;
		this.cvv = cvv;
		this.metodosPagoUsuario = metodosPagoUsuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(int numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public Date getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(Date fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public Usuario getMetodosPagoUsuario() {
		return metodosPagoUsuario;
	}

	public void setMetodosPagoUsuario(Usuario metodosPagoUsuario) {
		this.metodosPagoUsuario = metodosPagoUsuario;
	}

}
