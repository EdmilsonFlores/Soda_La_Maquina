package com.sodalamaquina.controller;
import com.sodalamaquina.service.ReporteService;
import java.time.LocalDate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reporte")
public class ReporteController {
    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    
    @GetMapping("/ventas")
    public String reporteVentas(@RequestParam(required = false) LocalDate inicio,
            @RequestParam(required = false) LocalDate fin, Model model) {
        if (inicio != null && fin != null) {
            model.addAttribute("total", reporteService.totalVentas(inicio, fin));
            model.addAttribute("transacciones", reporteService.cantidadTransacciones(inicio, fin));
            model.addAttribute("inicio", inicio);
            model.addAttribute("fin", fin);
        }
        return "/reporte/ventas";
    }

   
    @GetMapping("/insumos")
    public String reporteInsumos(@RequestParam(required = false) LocalDate inicio,
            @RequestParam(required = false) LocalDate fin, Model model) {
        if (inicio != null && fin != null) {
            model.addAttribute("insumos", reporteService.topInsumos(inicio, fin, 10));
            model.addAttribute("inicio", inicio);
            model.addAttribute("fin", fin);
        }
        return "/reporte/insumos";
    }
}