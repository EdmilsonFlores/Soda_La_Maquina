package com.sodalamaquina.controller;

import com.sodalamaquina.domain.Proveedor;
import com.sodalamaquina.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/proveedor")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = proveedorService.getProveedores();
        model.addAttribute("proveedores", lista);
        return "proveedor/listado";
    }

    @GetMapping("/nuevo")
    public String nuevoProveedor(Proveedor proveedor) {
        return "proveedor/form";
    }

    @PostMapping("/guardar")
    public String guardarProveedor(Proveedor proveedor) {
        proveedorService.save(proveedor);
        return "redirect:/proveedor/listado";
    }

    @GetMapping("/eliminar/{idProveedor}")
    public String eliminarProveedor(Proveedor proveedor) {
        proveedorService.delete(proveedor);
        return "redirect:/proveedor/listado";
    }

    @GetMapping("/modificar/{idProveedor}")
    public String modificarProveedor(Proveedor proveedor, Model model) {
        proveedor = proveedorService.getProveedor(proveedor);
        model.addAttribute("proveedor", proveedor);
        return "proveedor/form";
    }
}
