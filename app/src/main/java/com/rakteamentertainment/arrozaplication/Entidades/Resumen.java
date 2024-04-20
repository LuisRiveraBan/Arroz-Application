package com.rakteamentertainment.arrozaplication.Entidades;

public class Resumen {

    private Integer id;
    private String fecha;
    private String hora;
    private int idUsuario;
    private int idProducto;
    private double totalDinero;
    private double totalSacos;
    private double tara;
    private double sumaSacos;
    private  double precio;

    public Resumen() {
    }

    public Resumen(Integer id, String fecha, String hora, int idUsuario, int idProducto, double totalDinero, double totalSacos, double tara, double sumaSacos, double precio) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.idUsuario = idUsuario;
        this.idProducto = idProducto;
        this.totalDinero = totalDinero;
        this.totalSacos = totalSacos;
        this.tara = tara;
        this.sumaSacos = sumaSacos;
        this.precio = precio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public double getTotalDinero() {
        return totalDinero;
    }

    public void setTotalDinero(double totalDinero) {
        this.totalDinero = totalDinero;
    }

    public double getTotalSacos() {
        return totalSacos;
    }

    public void setTotalSacos(double totalSacos) {
        this.totalSacos = totalSacos;
    }

    public double getTara() {
        return tara;
    }

    public void setTara(double tara) {
        this.tara = tara;
    }

    public double getSumaSacos() {
        return sumaSacos;
    }

    public void setSumaSacos(double sumaSacos) {
        this.sumaSacos = sumaSacos;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
