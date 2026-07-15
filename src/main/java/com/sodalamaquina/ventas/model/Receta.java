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
@Table(name = "receta")
@Data
public class Receta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReceta;

    @OneToOne
    @JoinColumn(name = "idProducto", nullable = false)
    private Producto producto;
}
