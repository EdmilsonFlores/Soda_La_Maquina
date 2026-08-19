package com.sodalamaquina.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "receta")
public class Receta implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReceta;

    @OneToOne
    @JoinColumn(name = "id_producto", unique = true)
    private Producto producto;

    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleReceta> detalles = new ArrayList<>();

    public Integer getIdReceta() {
        return idReceta;
    }
    public void setIdReceta(Integer idReceta) {
        this.idReceta = idReceta;
    }
    public Producto getProducto() {
        return producto;
    }
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    public List<DetalleReceta> getDetalles() {
        return detalles;
    }
    public void setDetalles(List<DetalleReceta> detalles) {
        this.detalles = detalles;
    }
}