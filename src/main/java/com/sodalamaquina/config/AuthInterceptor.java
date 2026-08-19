package com.sodalamaquina.config;

import com.sodalamaquina.controller.LoginController;
import com.sodalamaquina.domain.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        Usuario usuario = session != null ? (Usuario) session.getAttribute(LoginController.SESSION_USUARIO) : null;

        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }

        String rol = usuario.getRol() != null ? usuario.getRol().getNombre() : "";
        String path = request.getRequestURI().substring(request.getContextPath().length());

        boolean permitido = "Admin".equals(rol)
                || (esRutaCocinero(path) && "Cocinero".equals(rol))
                || (esRutaCajero(path) && "Cajero".equals(rol));

        if (!permitido) {
            response.sendRedirect(request.getContextPath() + rutaHomeSegunRol(rol));
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                modelAndView.addObject("sessionUsuario", session.getAttribute(LoginController.SESSION_USUARIO));
            }
        }
    }

    private boolean esRutaCocinero(String path) {
        return path.startsWith("/insumo") || path.startsWith("/categoria");
    }

    private boolean esRutaCajero(String path) {
        return path.startsWith("/venta");
    }

    private String rutaHomeSegunRol(String rol) {
    if ("Cocinero".equals(rol)) return "/insumo/listado";
    if ("Cajero".equals(rol)) return "/venta/pos";
    if ("admin".equals(rol)) return "/Admin/dashboard"; 
    return "/login";
}
    
}