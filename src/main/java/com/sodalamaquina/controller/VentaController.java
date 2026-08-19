package com.sodalamaquina.controller;

import com.sodalamaquina.domain.Producto;
import com.sodalamaquina.domain.Usuario;
import com.sodalamaquina.domain.Venta;
import com.sodalamaquina.dto.CarritoItem;
import com.sodalamaquina.service.ProductoService;
import com.sodalamaquina.service.UsuarioService;
import com.sodalamaquina.service.VentaService;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/venta")
public class VentaController {

    private static final String CARRITO_SESSION = "carritoVenta";

    private final VentaService ventaService;
    private final ProductoService productoService;
    private final UsuarioService usuarioService;

    public VentaController(VentaService ventaService,
            ProductoService productoService,
            UsuarioService usuarioService) {
        this.ventaService = ventaService;
        this.productoService = productoService;
        this.usuarioService = usuarioService;
    }

    // HU-16: cuadrícula de platos + carrito actual + lista de cajeros para cobrar
    @GetMapping("/pos")
    public String pos(HttpSession session, Model model) {
        List<CarritoItem> carrito = getCarrito(session);
        model.addAttribute("productos", productoService.getProductos(true));
        model.addAttribute("usuarios", usuarioService.getUsuarios());
        model.addAttribute("carrito", carrito);
        model.addAttribute("total", calcularTotal(carrito));
        return "/venta/pos";
    }

    // HU-17 paso 1: agregar un plato al carrito
    @PostMapping("/agregar")
    public String agregar(@RequestParam Integer idProducto,
            @RequestParam(defaultValue = "1") Integer cantidad,
            HttpSession session) {
        Producto producto = productoService.getProducto(idProducto);
        if (producto != null) {
            List<CarritoItem> carrito = getCarrito(session);
            carrito.add(new CarritoItem(producto.getIdProducto(), producto.getNombreProducto(),
                    cantidad, producto.getPrecioVenta()));
        }
        return "redirect:/venta/pos";
    }

    @GetMapping("/quitar/{index}")
    public String quitar(@PathVariable Integer index, HttpSession session) {
        List<CarritoItem> carrito = getCarrito(session);
        if (index >= 0 && index < carrito.size()) {
            carrito.remove((int) index);
        }
        return "redirect:/venta/pos";
    }

    // HU-17 paso 2: botón "Cobrar" -> guarda la venta y dispara HU-20
    @PostMapping("/cobrar")
    public String cobrar(@RequestParam Long idUsuario, HttpSession session) {
        List<CarritoItem> carrito = getCarrito(session);
        if (carrito.isEmpty()) {
            return "redirect:/venta/pos";
        }

        Usuario usuarioBuscado = new Usuario();
        usuarioBuscado.setIdUsuario(idUsuario);
        Usuario usuario = usuarioService.getUsuario(usuarioBuscado);

        Venta venta = ventaService.registrarVenta(usuario, carrito);

        session.removeAttribute(CARRITO_SESSION);
        return "redirect:/venta/recibo/" + venta.getIdVenta();
    }

    @GetMapping("/recibo/{idVenta}")
    public String recibo(@PathVariable Integer idVenta, Model model) {
        model.addAttribute("venta", ventaService.getVenta(idVenta));
        return "/venta/recibo";
    }

    @SuppressWarnings("unchecked")
    private List<CarritoItem> getCarrito(HttpSession session) {
        List<CarritoItem> carrito = (List<CarritoItem>) session.getAttribute(CARRITO_SESSION);
        if (carrito == null) {
            carrito = new ArrayList<>();
            session.setAttribute(CARRITO_SESSION, carrito);
        }
        return carrito;
    }

    private double calcularTotal(List<CarritoItem> carrito) {
        double total = 0;
        for (CarritoItem item : carrito) {
            total += item.getSubtotal();
        }
        return total;
    }
}