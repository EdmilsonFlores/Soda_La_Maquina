/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sodalamaquina.ventas.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "detalle_receta")
@Data
public class DetalleReceta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleReceta;

    @ManyToOne
    @JoinColumn(name = "idReceta", nullable = false)
    private Receta receta;

    // TEMPORAL: se reemplaza por @ManyToOne a la clase Insumo real
    // cuando el compañero encargado de Insumos suba su parte y se haga merge.
    @Column(nullable = false)
    private Long idInsumo;

    @Column(nullable = false)
    private Double cantidadUtilizada;
}