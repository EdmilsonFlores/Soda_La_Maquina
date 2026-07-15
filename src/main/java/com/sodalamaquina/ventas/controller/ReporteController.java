/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sodalamaquina.ventas.controller;
import com.sodalamaquina.ventas.model.Venta;
import com.sodalamaquina.ventas.repository.VentaRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
/**
 *
 * @author HP
 */
@RestController
@RequestMapping("/api/reportes")
public class ReporteController {
    private final VentaRepository ventaRepository;

    public ReporteController(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    
    @GetMapping("/ventas")
    public Map<String, Object> reporteVentas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) {

        LocalDateTime inicio = desde.atStartOfDay();
        LocalDateTime fin = hasta.atTime(23, 59, 59);

        List<Venta> ventas = ventaRepository.findByFechaVentaBetween(inicio, fin);

        double totalRecaudado = ventas.stream().mapToDouble(Venta::getTotalVenta).sum();

        return Map.of(
                "totalColones", totalRecaudado,
                "cantidadTransacciones", ventas.size(),
                "ventas", ventas
        );
    }

    
    @GetMapping("/insumos-mas-gastados")
    public String reporteInsumosGastados() {
        return "Pendiente: requiere integración con el módulo de Insumos (MovimientoInventario)";
    }
}
