package org.developerjs.refreshapp.pojo;

public class Noticia extends Item{

    private String titulo ;
    private String fecha;
    private String contenido;
    private String video;
    private String foto;

    public Noticia() {
    }

    public Noticia(String titulo, String fecha, String contenido, String video, String foto) {
        this.titulo = titulo;
        this.fecha = fecha;
        this.contenido = contenido;
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

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
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
