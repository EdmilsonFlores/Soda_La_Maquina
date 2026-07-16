package com.sodalamaquina.controller;

import com.sodalamaquina.domain.Rol;
import com.sodalamaquina.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rol")
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = rolService.getRoles();
        model.addAttribute("roles", lista);
        return "rol/listado";
    }

    @GetMapping("/nuevo")
    public String nuevoRol(Rol rol) {
        return "rol/form";
    }

    @PostMapping("/guardar")
    public String guardarRol(Rol rol) {
        rolService.save(rol);
        return "redirect:/rol/listado";
    }

    @GetMapping("/eliminar/{idRol}")
    public String eliminarRol(Rol rol) {
        rolService.delete(rol);
        return "redirect:/rol/listado";
    }

    @GetMapping("/modificar/{idRol}")
    public String modificarRol(Rol rol, Model model) {
        rol = rolService.getRol(rol);
        model.addAttribute("rol", rol);
        return "rol/form";
    }
}
