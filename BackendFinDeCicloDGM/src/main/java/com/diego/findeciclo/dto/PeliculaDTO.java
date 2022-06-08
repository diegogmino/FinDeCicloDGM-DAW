package com.diego.findeciclo.dto;

import java.math.BigDecimal;

public class PeliculaDTO {

    private Integer id;
    private String titulo;
    private String portada;
    private BigDecimal precio;

    public PeliculaDTO() {
    }

    public PeliculaDTO(Integer id, String titulo, String portada, BigDecimal precio) {
        this.id = id;
        this.titulo = titulo;
        this.portada = portada;
        this.precio = precio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

}
