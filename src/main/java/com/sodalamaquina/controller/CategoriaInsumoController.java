package com.sodalamaquina.controller;

import com.sodalamaquina.service.CategoriaInsumoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.sodalamaquina.domain.CategoriaInsumo;

@Controller
@RequestMapping("/categoria")
public class CategoriaInsumoController {

    private final CategoriaInsumoService categoriaService;

    public CategoriaInsumoController(CategoriaInsumoService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/listado")
    public String listado(Model model) {

    model.addAttribute(
            "categorias",
            categoriaService.getCategorias(false)
    );

    model.addAttribute(
            "categoria",
            new CategoriaInsumo()
    );

    return "/categoria/listado";
    }
    
    @PostMapping("/guardar")
    public String guardar(CategoriaInsumo categoria) {

    categoriaService.save(categoria);

    return "redirect:/categoria/listado";
    }
    
    @GetMapping("/modificar/{idCategoria}")
    public String modificar(@PathVariable Integer idCategoria, Model model) {

    CategoriaInsumo categoria = categoriaService.getCategoria(idCategoria);

    model.addAttribute("categoria", categoria);

    return "/categoria/modifica";
    }
    
    @GetMapping("/eliminar/{idCategoria}")
    public String eliminar(@PathVariable Integer idCategoria) {

    categoriaService.delete(idCategoria);

    return "redirect:/categoria/listado";
    }
    

    
    
}