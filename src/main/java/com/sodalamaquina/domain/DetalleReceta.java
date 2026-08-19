package com.sodalamaquina.domain;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "detalle_receta")
public class DetalleReceta implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetalleReceta;

    @ManyToOne
    @JoinColumn(name = "id_receta")
    private Receta receta;

    @ManyToOne
    @JoinColumn(name = "id_insumo")
    private Insumo insumo;

    private Double cantidadUtilizada;

    public Integer getIdDetalleReceta() {
        return idDetalleReceta;
    }
    public void setIdDetalleReceta(Integer idDetalleReceta) {
        this.idDetalleReceta = idDetalleReceta;
    }
    public Receta getReceta() {
        return receta;
    }
    public void setReceta(Receta receta) {
        this.receta = receta;
    }
    public Insumo getInsumo() {
        return insumo;
    }
    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }
    public Double getCantidadUtilizada() {
        return cantidadUtilizada;
    }
    public void setCantidadUtilizada(Double cantidadUtilizada) {
        this.cantidadUtilizada = cantidadUtilizada;
    }
}