package com.rakteamentertainment.arrozaplication.Entidades;

public class Producto {
    private Integer id;
    private int idUsuario;
    private double[] linea; // Array para almacenar las 50 columnas 'linea'

    // Constructor
    public Producto(Integer id, int idUsuario, double[] linea) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.linea = linea;
    }

    public Producto(Integer id, int idUsuario) {
        this.id = id;
        this.idUsuario = idUsuario;
    }

    public Producto(double[] linea) {
        this.linea = linea;
    }

    // Getters y setters para el atributo 'id'
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Getters y setters para el atributo 'idUsuario'
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    // Getters y setters para el atributo 'linea'
    public double[] getLinea() {
        return linea;
    }

    public void setLinea(double[] linea) {
        this.linea = linea;
    }
}

