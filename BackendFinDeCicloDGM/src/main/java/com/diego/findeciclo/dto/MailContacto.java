package com.diego.findeciclo.dto;

public class MailContacto {
    
    private String nombre;
    private String apellidos;
    private String mail;
    private String pais;
    private String mensaje;
    
    public MailContacto() {
    }

    public MailContacto(String nombre, String apellidos, String mail, String pais, String mensaje) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.mail = mail;
        this.pais = pais;
        this.mensaje = mensaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
