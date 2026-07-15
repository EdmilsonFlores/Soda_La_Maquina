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
@Table(name = "producto")
@Data
public class Producto {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    @Column(nullable = false)
    private String nombreProducto;

    @Column(nullable = false)
    private Double precioVenta;

    private String estado; // ACTIVO / INACTIVO
}
