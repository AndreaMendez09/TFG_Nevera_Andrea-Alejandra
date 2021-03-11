package com.example.nevera_andreaalejandra.Models;

import java.util.Date;

public class ProductoModelo {
    private int cantidad;
    private String tipo;
    private String nombre;
    private Date fecha;
    private double precio;
    private String ubicacion;

    //Creamos el constructor vacio
    public ProductoModelo() {

    }

    //Creamos el constructor solo con los not null
    public ProductoModelo(int cantidad, String tipo, String nombre, String ubicacion) {
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
    }

    //Creamos los getters y setters
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
