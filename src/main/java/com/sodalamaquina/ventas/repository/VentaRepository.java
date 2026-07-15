/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sodalamaquina.ventas.repository;
import com.sodalamaquina.ventas.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;
/**
 *
 * @author HP
 */
public interface VentaRepository  extends JpaRepository<Venta, Long> {
     List<Venta> findByFechaVentaBetween(LocalDateTime inicio, LocalDateTime fin);
}
