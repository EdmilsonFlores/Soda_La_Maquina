package com.sodalamaquina.controller;

import com.sodalamaquina.domain.Insumo;
import com.sodalamaquina.service.InsumoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.sodalamaquina.service.CategoriaInsumoService;
import com.sodalamaquina.service.InsumoService;


@Controller
@RequestMapping("/insumo")
public class InsumoController {

    private final InsumoService insumoService;
    private final CategoriaInsumoService categoriaService;
    

    public InsumoController(
        InsumoService insumoService,
        CategoriaInsumoService categoriaService) {

        this.insumoService = insumoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/listado")
    public String listado(Model model) {

        model.addAttribute(
                "insumos",
                insumoService.getInsumos(false)
        );

        model.addAttribute(
                "insumo",
                new Insumo()
        );
        
        model.addAttribute(
        "categorias",
        categoriaService.getCategorias(true)
        );
        

        return "/insumo/listado";
    }
    
    @PostMapping("/guardar")
        public String guardar(Insumo insumo) {

            insumoService.save(insumo);

        return "redirect:/insumo/listado";
    }
    
    @GetMapping("/eliminar/{idInsumo}")
        public String eliminar(@PathVariable Integer idInsumo) {

        insumoService.delete(idInsumo);

        return "redirect:/insumo/listado";
    }
    
    @GetMapping("/modificar/{idInsumo}")
    public String modificar(@PathVariable Integer idInsumo,
        Model model) {

        Insumo insumo = insumoService.getInsumo(idInsumo);

        model.addAttribute("insumo", insumo);

        model.addAttribute(
                "insumos",
                insumoService.getInsumos(false)
        );
        
        model.addAttribute(
        "categorias",
        categoriaService.getCategorias(true)
        );

    return "/insumo/listado";
    }
    
}