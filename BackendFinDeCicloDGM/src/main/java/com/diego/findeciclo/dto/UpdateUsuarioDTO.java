package com.diego.findeciclo.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

import com.diego.findeciclo.model.MetodoPago;

public class UpdateUsuarioDTO {
    
    private String nombre;
	private String apellido;
	private String email;
    private String contrasena;
	private String telefono;
	private String direccion;
	private String pais;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "metodosPagoUsuario")
	private List<MetodoPago> metodoDePago;

    public UpdateUsuarioDTO() {
    }

    public UpdateUsuarioDTO(String nombre, String apellido, String email, String contrasena, String telefono,
            String direccion, String pais, List<MetodoPago> metodoDePago) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasena = contrasena;
        this.telefono = telefono;
        this.direccion = direccion;
        this.pais = pais;
        this.metodoDePago = metodoDePago;
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

    public List<MetodoPago> getMetodoDePago() {
        return metodoDePago;
    }

    public void setMetodoDePago(List<MetodoPago> metodoDePago) {
        this.metodoDePago = metodoDePago;
    }

    

}
