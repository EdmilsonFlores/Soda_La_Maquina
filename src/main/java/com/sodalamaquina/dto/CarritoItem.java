package com.sodalamaquina.dto;
import java.io.Serializable;

public class CarritoItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer idProducto;
    private String nombreProducto;
    private Integer cantidad;
    private Double precioUnitario;

    public CarritoItem() {
    }

    public CarritoItem(Integer idProducto, String nombreProducto, Integer cantidad, Double precioUnitario) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public Double getSubtotal() {
        return precioUnitario * cantidad;
    }

    public Integer getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }
    public String getNombreProducto() {
        return nombreProducto;
    }
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    public Integer getCantidad() {
        return cantidad;
    }
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    public Double getPrecioUnitario() {
        return precioUnitario;
    }
    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}