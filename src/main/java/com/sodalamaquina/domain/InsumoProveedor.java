package com.sodalamaquina.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "insumo_proveedor")
public class InsumoProveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_insumo_proveedor")
    private Long idInsumoProveedor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_insumo")
    private Insumo insumo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_proveedor")
    private Proveedor proveedor;

    @Column(name = "precio_compra")
    private Double precioCompra;

    @Column(name = "tiempo_entrega_dias")
    private Integer tiempoEntregaDias;

    @Column(name = "activo")
    private Boolean activo = true;

    public InsumoProveedor() {
    }

    public Long getIdInsumoProveedor() {
        return idInsumoProveedor;
    }

    public void setIdInsumoProveedor(Long idInsumoProveedor) {
        this.idInsumoProveedor = idInsumoProveedor;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public Integer getTiempoEntregaDias() {
        return tiempoEntregaDias;
    }

    public void setTiempoEntregaDias(Integer tiempoEntregaDias) {
        this.tiempoEntregaDias = tiempoEntregaDias;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
