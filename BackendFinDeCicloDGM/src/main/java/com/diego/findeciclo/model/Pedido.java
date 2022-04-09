package com.diego.findeciclo.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "pedidos")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String direccionEnvio;
	private Date fechaPedido;
	private BigDecimal precioTotal;
	private boolean entregado;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "peliculas_pedidos",
			joinColumns = @JoinColumn(name="idPedido"),
			inverseJoinColumns = @JoinColumn(name="idPelicula")
	)
	private List<Pelicula> peliculas;
	
	@ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
	@JsonIgnore
	private Usuario pedidosUsuario;

	public Pedido() {
		super();
	}

	public Pedido(String direccionEnvio, Date fechaPedido, BigDecimal precioTotal, boolean entregado,
			List<Pelicula> peliculas, Usuario pedidosUsuario) {
		super();
		this.direccionEnvio = direccionEnvio;
		this.fechaPedido = fechaPedido;
		this.precioTotal = precioTotal;
		this.entregado = entregado;
		this.peliculas = peliculas;
		this.pedidosUsuario = pedidosUsuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDireccionEnvio() {
		return direccionEnvio;
	}

	public void setDireccionEnvio(String direccionEnvio) {
		this.direccionEnvio = direccionEnvio;
	}

	public Date getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public BigDecimal getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(BigDecimal precioTotal) {
		this.precioTotal = precioTotal;
	}

	public boolean isEntregado() {
		return entregado;
	}

	public void setEntregado(boolean entregado) {
		this.entregado = entregado;
	}

	public List<Pelicula> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(List<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}

	public Usuario getPedidosUsuario() {
		return pedidosUsuario;
	}

	public void setPedidosUsuario(Usuario pedidosUsuario) {
		this.pedidosUsuario = pedidosUsuario;
	}
	
	
}
