package com.sodalamaquina.controller;

import com.sodalamaquina.service.CategoriaInsumoService;
import com.sodalamaquina.service.InsumoService;
import com.sodalamaquina.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private InsumoService insumoService;

    @Autowired
    private CategoriaInsumoService categoriaInsumoService;

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping("/")
    public String index(Model model) {
        var listaInsumos = insumoService.getInsumos();
        var listaCategorias = categoriaInsumoService.getCategorias(true);
        var listaProveedores = proveedorService.getProveedores();

        // Filtrar insumos donde la cantidad actual es menor o igual al mínimo
        var listaStockBajo = listaInsumos.stream()
                .filter(i -> i.getCantidadActual() != null
                && i.getAlertaMinima() != null
                && i.getCantidadActual() <= i.getAlertaMinima())
                .toList();

        // Pasar totales y lista de alertas a la vista
        model.addAttribute("totalInsumos", listaInsumos.size());
        model.addAttribute("totalCategorias", listaCategorias.size());
        model.addAttribute("totalProveedores", listaProveedores.size());
        model.addAttribute("totalStockBajo", listaStockBajo.size());
        model.addAttribute("insumosStockBajo", listaStockBajo);

        return "index/index";
    }
}
