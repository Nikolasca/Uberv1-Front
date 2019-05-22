package com.example.uberv1;

import com.google.gson.annotations.SerializedName;

public class Usuario {
    @SerializedName("usuario")
    private String usuario;
    @SerializedName("password")
    private String password;
    @SerializedName("tipo_Usuario")
    private String tipo_Usuario;
    @SerializedName("telefono")
    private String telefono;
    @SerializedName("lat")
    private String lat;
    @SerializedName("long")
    private String longs;

    public Usuario(String usuario, String password, String tipo_Usuario, String telefono, String lat, String longs) {
        this.usuario = usuario;
        this.password = password;
        this.tipo_Usuario = tipo_Usuario;
        this.telefono = telefono;
        this.lat = lat;
        this.longs = longs;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipo_Usuario() {
        return tipo_Usuario;
    }

    public void setTipo_Usuario(String tipo_Usuario) {
        this.tipo_Usuario = tipo_Usuario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongs() {
        return longs;
    }

    public void setLongs(String longs) {
        this.longs = longs;
    }
}
