package org.developerjs.refreshapp.pojo;

import android.util.ArrayMap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Grupo extends Item implements Serializable {

    private String foto;
    private String nombre;
    private String descripcion;
    private String celular;
    private Social social;
    private Date create;
    private Date update;

    public Grupo() {
    }

    public Grupo(String foto, String nombre, String descripcion, String celular, Social social, Date create, Date update) {
        this.foto = foto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.celular = celular;
        this.social = social;
        this.create = create;
        this.update = update;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
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

    public Social getSocial() {
        return social;
    }

    public void setSocial(Social social) {
        this.social = social;
    }
}
