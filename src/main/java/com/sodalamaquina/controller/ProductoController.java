package com.sodalamaquina.controller;

import com.sodalamaquina.domain.Producto;
import com.sodalamaquina.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/producto")

public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("productos", productoService.getProductos(false));
        model.addAttribute("producto", new Producto());
        return "/producto/listado";
    }

    @PostMapping("/guardar")
    public String guardar(Producto producto) {
        productoService.save(producto);
        return "redirect:/producto/listado";
    }

    @GetMapping("/eliminar/{idProducto}")
    public String eliminar(@PathVariable Integer idProducto) {
        productoService.delete(idProducto);
        return "redirect:/producto/listado";
    }

    @GetMapping("/modificar/{idProducto}")
    public String modificar(@PathVariable Integer idProducto, Model model) {
        model.addAttribute("producto", productoService.getProducto(idProducto));
        model.addAttribute("productos", productoService.getProductos(false));
        return "/producto/listado";
    }
}