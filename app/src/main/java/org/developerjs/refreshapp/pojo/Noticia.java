package org.developerjs.refreshapp.pojo;

import com.google.firebase.Timestamp;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Noticia extends Item implements Serializable {

    private String titulo ;
    private String fuente;
    private String contenido;
    private String video;
    private String foto;
    private Date create;
    private Date update;

    public Noticia() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
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

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public Date getUpdate() {
        return update;
    }

    public void setUpdate(Date update) {
        this.update = update;
    }

    @Override
    public String toString() {
        return "Noticia{" +
                "titulo='" + titulo + '\'' +
                ", fuente='" + fuente + '\'' +
                ", contenido='" + contenido + '\'' +
                ", video='" + video + '\'' +
                ", foto='" + foto + '\'' +
                ", create=" + create +
                ", update=" + update +
                '}';
    }
}
