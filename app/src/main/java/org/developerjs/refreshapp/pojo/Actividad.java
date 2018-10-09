package org.developerjs.refreshapp.pojo;

public class Actividad {
    private String titulo;
    private String fecha;
    private String fecha_actividad;
    private String video;
    private String foto;

    public Actividad() {
    }

    public Actividad(String titulo, String fecha, String fecha_actividad, String video, String foto) {
        this.titulo = titulo;
        this.fecha = fecha;
        this.fecha_actividad = fecha_actividad;
        this.video = video;
        this.foto = foto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFecha_actividad() {
        return fecha_actividad;
    }

    public void setFecha_actividad(String fecha_actividad) {
        this.fecha_actividad = fecha_actividad;
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
