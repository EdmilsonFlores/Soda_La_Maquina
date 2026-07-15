/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sodalamaquina.ventas.controller;
import com.sodalamaquina.ventas.model.Producto;
import com.sodalamaquina.ventas.model.Venta;
import com.sodalamaquina.ventas.model.DetalleVenta;
import com.sodalamaquina.ventas.dto.VentaRequest;
import com.sodalamaquina.ventas.repository.ProductoRepository;
import com.sodalamaquina.ventas.service.VentaService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
/**
 *
 * @author HP
 */
@RestController
@RequestMapping("/api/pos")
public class PosController {
      private final ProductoRepository productoRepository;
    private final VentaService ventaService;

    public PosController(ProductoRepository productoRepository, VentaService ventaService) {
        this.productoRepository = productoRepository;
        this.ventaService = ventaService;
    }

  
    @GetMapping("/productos")
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

   
    @PostMapping("/venta")
    public Venta registrarVenta(@RequestBody VentaRequest request) {
        return ventaService.registrarVenta(request.getVenta(), request.getDetalles());
    }
}
