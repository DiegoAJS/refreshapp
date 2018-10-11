package org.developerjs.refreshapp.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Actividad extends Item {
    private String titulo;
    private String contenido;
    private String tipo_actividad;
    private String lugar;
    private String localizacion;
    private Date fecha_actividad;
    private Date fecha_publicacion;
    private String video;
    private String foto;

    public Actividad() {
    }

    public Actividad(String titulo, String contenido, String tipo_actividad, String lugar, String localizacion, Date fecha_actividad, Date fecha_publicacion, String video, String foto) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.tipo_actividad = tipo_actividad;
        this.lugar = lugar;
        this.localizacion = localizacion;
        this.fecha_actividad = fecha_actividad;
        this.fecha_publicacion = fecha_publicacion;
        this.video = video;
        this.foto = foto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getTipo_actividad() {
        return tipo_actividad;
    }

    public void setTipo_actividad(String tipo_actividad) {
        this.tipo_actividad = tipo_actividad;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public Date getFecha_actividad() {
        return fecha_actividad;
    }

    public void setFecha_actividad(Date fecha_actividad) {
        this.fecha_actividad = fecha_actividad;
    }

    public Date getFecha_publicacion() {
        return fecha_publicacion;
    }

    public void setFecha_publicacion(Date fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
