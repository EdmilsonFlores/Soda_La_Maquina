package com.sodalamaquina.controller;

import com.sodalamaquina.domain.Insumo;
import com.sodalamaquina.service.InsumoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/insumo")
public class InsumoController {

    private final InsumoService insumoService;

    public InsumoController(InsumoService insumoService) {
        this.insumoService = insumoService;
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

        return "/insumo/listado";
    }
    
    @PostMapping("/guardar")
    public String guardar(Insumo insumo) {

        insumoService.save(insumo);

    return "redirect:/insumo/listado";
}
}