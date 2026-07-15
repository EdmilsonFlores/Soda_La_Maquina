package com.sodalamaquina.domain;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "insumo")
public class Insumo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idInsumo;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private CategoriaInsumo categoria;

    @Column(nullable = false, length = 100)
    private String nombre;

    private Double cantidadActual;

    @Column(length = 20)
    private String unidadMedida;

    private Double precioCosto;

    private Double alertaMinima;

    private boolean activo = true;

    public Integer getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(Integer idInsumo) {
        this.idInsumo = idInsumo;
    }

    public CategoriaInsumo getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaInsumo categoria) {
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getCantidadActual() {
        return cantidadActual;
    }

    public void setCantidadActual(Double cantidadActual) {
        this.cantidadActual = cantidadActual;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public Double getPrecioCosto() {
        return precioCosto;
    }

    public void setPrecioCosto(Double precioCosto) {
        this.precioCosto = precioCosto;
    }

    public Double getAlertaMinima() {
        return alertaMinima;
    }

    public void setAlertaMinima(Double alertaMinima) {
        this.alertaMinima = alertaMinima;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}