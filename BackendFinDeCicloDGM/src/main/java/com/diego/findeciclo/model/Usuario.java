package com.diego.findeciclo.model;

import java.sql.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String apellido;
	private String email;
	private String contrasena;
	private String telefono;
	private String direccion;
	private String pais;
	private Date fechaRegistro;
	
	@ManyToOne
    @JoinColumn(name="idPerfil")
	private Perfil perfil;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "metodosPagoUsuario")
	private List<MetodoPago> metodoDePago;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pedidosUsuario")
	private List<Pedido> pedidos;

	public Usuario() {
		super();
	}

	public Usuario(String nombre, String apellido, String email, String contrasena, String telefono, String direccion, String pais,
			Date fechaRegistro, Perfil perfil, List<MetodoPago> metodoDePago, List<Pedido> pedidos) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.contrasena = contrasena;
		this.telefono = telefono;
		this.direccion = direccion;
		this.pais = pais;
		this.fechaRegistro = fechaRegistro;
		this.perfil = perfil;
		this.metodoDePago = metodoDePago;
		this.pedidos = pedidos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public List<MetodoPago> getMetodoDePago() {
		return metodoDePago;
	}

	public void setMetodoDePago(List<MetodoPago> metodoDePago) {
		this.metodoDePago = metodoDePago;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

}
