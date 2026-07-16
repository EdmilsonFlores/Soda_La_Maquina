package com.sodalamaquina.controller;

import com.sodalamaquina.domain.InsumoProveedor;
import com.sodalamaquina.service.InsumoProveedorService;
import com.sodalamaquina.service.InsumoService;
import com.sodalamaquina.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/insumoProveedor")
public class InsumoProveedorController {

    @Autowired
    private InsumoProveedorService insumoProveedorService;

    @Autowired
    private InsumoService insumoService;

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = insumoProveedorService.getInsumosProveedor();
        model.addAttribute("insumosProveedor", lista);
        return "insumoProveedor/listado";
    }

    @GetMapping("/nuevo")
    public String nuevoInsumoProveedor(InsumoProveedor insumoProveedor, Model model) {
        model.addAttribute("insumos", insumoService.getInsumos());
        model.addAttribute("proveedores", proveedorService.getProveedores());
        return "insumoProveedor/form";
    }

    @PostMapping("/guardar")
    public String guardarInsumoProveedor(InsumoProveedor insumoProveedor) {
        insumoProveedorService.save(insumoProveedor);
        return "redirect:/insumoProveedor/listado";
    }

    @GetMapping("/eliminar/{idInsumoProveedor}")
    public String eliminarInsumoProveedor(InsumoProveedor insumoProveedor) {
        insumoProveedorService.delete(insumoProveedor);
        return "redirect:/insumoProveedor/listado";
    }

    @GetMapping("/modificar/{idInsumoProveedor}")
    public String modificarInsumoProveedor(InsumoProveedor insumoProveedor, Model model) {
        insumoProveedor = insumoProveedorService.getInsumoProveedor(insumoProveedor);
        model.addAttribute("insumoProveedor", insumoProveedor);
        model.addAttribute("insumos", insumoService.getInsumos());
        model.addAttribute("proveedores", proveedorService.getProveedores());
        return "insumoProveedor/form";
    }
}
