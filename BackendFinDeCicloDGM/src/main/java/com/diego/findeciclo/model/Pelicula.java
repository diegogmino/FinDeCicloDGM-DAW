package com.diego.findeciclo.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "peliculas")
public class Pelicula {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer codigoBarras;
	private String titulo;
	private BigDecimal precio;
	private String portada;
	private boolean destacada;
	private int unidades;
	private String genero;
	private String sinopsis;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "peliculas_directores",
			joinColumns = @JoinColumn(name="idPelicula"),
			inverseJoinColumns = @JoinColumn(name="idDirector")
	)
	private List<Director> directores;

	public Pelicula() {
		super();
	}

	public Pelicula(Integer codigoBarras, String titulo, BigDecimal precio, String portada, boolean destacada,
			int unidades, String genero, String sinopsis, List<Director> directores) {
		super();
		this.codigoBarras = codigoBarras;
		this.titulo = titulo;
		this.precio = precio;
		this.portada = portada;
		this.destacada = destacada;
		this.unidades = unidades;
		this.genero = genero;
		this.sinopsis = sinopsis;
		this.directores = directores;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(Integer codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public String getPortada() {
		return portada;
	}

	public void setPortada(String portada) {
		this.portada = portada;
	}

	public boolean isDestacada() {
		return destacada;
	}

	public void setDestacada(boolean destacada) {
		this.destacada = destacada;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public List<Director> getDirectores() {
		return directores;
	}

	public void setDirectores(List<Director> directores) {
		this.directores = directores;
	}

}
