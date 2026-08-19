package com.sodalamaquina.controller;

import com.sodalamaquina.domain.Receta;
import com.sodalamaquina.service.InsumoService;
import com.sodalamaquina.service.ProductoService;
import com.sodalamaquina.service.RecetaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/receta")
public class RecetaController {
    private final RecetaService recetaService;
    private final ProductoService productoService;
    private final InsumoService insumoService;

    public RecetaController(RecetaService recetaService,
            ProductoService productoService,
            InsumoService insumoService) {
        this.recetaService = recetaService;
        this.productoService = productoService;
        this.insumoService = insumoService;
    }

    
    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("productos", productoService.getProductos(true));
        return "/receta/listado";
    }

    
    @GetMapping("/editar/{idProducto}")
    public String editar(@PathVariable Integer idProducto, Model model) {
        model.addAttribute("producto", productoService.getProducto(idProducto));
        model.addAttribute("receta", recetaService.getRecetaPorProducto(idProducto));
        model.addAttribute("insumos", insumoService.getInsumos(true));
        return "/receta/form";
    }

    @PostMapping("/guardar")
    public String guardar(@RequestParam Integer idProducto,
            @RequestParam(required = false) Integer[] idInsumo,
            @RequestParam(required = false) Double[] cantidadUtilizada) {
        recetaService.guardar(idProducto, idInsumo, cantidadUtilizada);
        return "redirect:/receta/listado";
    }
}