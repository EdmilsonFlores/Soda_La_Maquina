/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sodalamaquina.ventas.service;
import com.sodalamaquina.ventas.model.*;
import com.sodalamaquina.ventas.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
/**
 *
 * @author HP
 */
@Service
public class VentaService {
    private final VentaRepository ventaRepository;
    private final DetalleVentaRepository detalleVentaRepository;
    private final RecetaRepository recetaRepository;
    private final DetalleRecetaRepository detalleRecetaRepository;

    public VentaService(VentaRepository ventaRepository,
                         DetalleVentaRepository detalleVentaRepository,
                         RecetaRepository recetaRepository,
                         DetalleRecetaRepository detalleRecetaRepository) {
        this.ventaRepository = ventaRepository;
        this.detalleVentaRepository = detalleVentaRepository;
        this.recetaRepository = recetaRepository;
        this.detalleRecetaRepository = detalleRecetaRepository;
    }

    @Transactional
    public Venta registrarVenta(Venta venta, List<DetalleVenta> detalles) {
        venta.setFechaVenta(LocalDateTime.now());

        double total = detalles.stream()
                .mapToDouble(d -> d.getProducto().getPrecioVenta() * d.getCantidad())
                .sum();
        venta.setTotalVenta(total);

        Venta ventaGuardada = ventaRepository.save(venta);

        for (DetalleVenta detalle : detalles) {
            detalle.setVenta(ventaGuardada);
            detalle.setSubtotal(detalle.getProducto().getPrecioVenta() * detalle.getCantidad());
            detalleVentaRepository.save(detalle);

            
            registrarDescuentoInsumos(detalle.getProducto().getIdProducto(), detalle.getCantidad());
        }

        return ventaGuardada;
    }

    private void registrarDescuentoInsumos(Long idProducto, int cantidadVendida) {
        Receta receta = recetaRepository.findByProductoIdProducto(idProducto)
                .orElseThrow(() -> new RuntimeException("No hay receta definida para este producto"));

        List<DetalleReceta> ingredientes = detalleRecetaRepository.findByRecetaIdReceta(receta.getIdReceta());

        for (DetalleReceta ingrediente : ingredientes) {
            double cantidadADescontar = ingrediente.getCantidadUtilizada() * cantidadVendida;

            //temporal
            System.out.println("Descontar insumo idInsumo=" + ingrediente.getIdInsumo()
                    + " cantidad=" + cantidadADescontar);
        }
    }
}
