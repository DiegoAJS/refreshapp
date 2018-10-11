package org.developerjs.refreshapp.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Circular extends Item {

    private String titulo;
    private Date fecha_publicacion;
    private Date fecha_circular;
    private String contenido;
    private String foto;

    public Circular() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFecha_publicacion() {
        return fecha_publicacion;
    }

    public void setFecha_publicacion(Date fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }

    public Date getFecha_circular() {
        return fecha_circular;
    }

    public void setFecha_circular(Date fecha_circular) {
        this.fecha_circular = fecha_circular;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
