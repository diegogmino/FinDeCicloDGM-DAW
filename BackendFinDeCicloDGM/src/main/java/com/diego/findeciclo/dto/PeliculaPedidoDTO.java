package com.diego.findeciclo.dto;

import java.math.BigDecimal;
import java.util.List;
import com.diego.findeciclo.model.Director;
import com.diego.findeciclo.model.Formato;

public class PeliculaPedidoDTO {

    private Integer id;

    private long codigoBarras;
    private String titulo;
    private BigDecimal precio;
    private String portada;
    private String portada_id;
    private boolean destacada;
    private int unidades;
    private String genero;
    private Formato formato;
    private String sinopsis;
    private List<Director> directores;
    private int qty;

    public PeliculaPedidoDTO() {
    }

    public PeliculaPedidoDTO(Integer id, long codigoBarras, String titulo, BigDecimal precio, String portada,
            String portada_id, boolean destacada, int unidades, String genero, Formato formato, String sinopsis,
            List<Director> directores, int qty) {
        this.id = id;
        this.codigoBarras = codigoBarras;
        this.titulo = titulo;
        this.precio = precio;
        this.portada = portada;
        this.portada_id = portada_id;
        this.destacada = destacada;
        this.unidades = unidades;
        this.genero = genero;
        this.formato = formato;
        this.sinopsis = sinopsis;
        this.directores = directores;
        this.qty = qty;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(long codigoBarras) {
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

    public String getPortada_id() {
        return portada_id;
    }

    public void setPortada_id(String portada_id) {
        this.portada_id = portada_id;
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

    public Formato getFormato() {
        return formato;
    }

    public void setFormato(Formato formato) {
        this.formato = formato;
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

}
