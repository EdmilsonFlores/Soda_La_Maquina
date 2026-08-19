package com.sodalamaquina.controller;

import com.sodalamaquina.domain.Usuario;
import com.sodalamaquina.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    public static final String SESSION_USUARIO = "usuarioLogueado";

    private final UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/login")
    public String mostrarLogin(HttpSession session) {
        if (session.getAttribute(SESSION_USUARIO) != null) {
            return "redirect:/";
        }
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            Model model) {
        Usuario usuario = usuarioService.buscarPorUsername(username);

        if (usuario == null || !usuario.getPassword().equals(password) || !Boolean.TRUE.equals(usuario.getActivo())) {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "login";
        }

        session.setAttribute(SESSION_USUARIO, usuario);
        return "redirect:" + rutaSegunRol(usuario);
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    private String rutaSegunRol(Usuario usuario) {
        String rol = usuario.getRol() != null ? usuario.getRol().getNombre() : "";
        switch (rol) {
            case "Cocinero":
                return "/insumo/listado";
            case "Cajero":
                return "/venta/pos";
            case "admin":
                return "/admin/dashboard";
            default:
                return "/";
        }
    }
}