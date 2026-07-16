package com.sodalamaquina.controller;

import com.sodalamaquina.domain.Usuario;
import com.sodalamaquina.service.RolService;
import com.sodalamaquina.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = usuarioService.getUsuarios();
        model.addAttribute("usuarios", lista);
        return "usuario/listado";
    }

    @GetMapping("/nuevo")
    public String nuevoUsuario(Usuario usuario, Model model) {
        model.addAttribute("roles", rolService.getRoles());
        return "usuario/form";
    }

    @PostMapping("/guardar")
    public String guardarUsuario(Usuario usuario) {
        usuarioService.save(usuario);
        return "redirect:/usuario/listado";
    }

    @GetMapping("/eliminar/{idUsuario}")
    public String eliminarUsuario(Usuario usuario) {
        usuarioService.delete(usuario);
        return "redirect:/usuario/listado";
    }

    @GetMapping("/modificar/{idUsuario}")
    public String modificarUsuario(Usuario usuario, Model model) {
        usuario = usuarioService.getUsuario(usuario);
        model.addAttribute("usuario", usuario);
        model.addAttribute("roles", rolService.getRoles());
        return "usuario/form";
    }
}
