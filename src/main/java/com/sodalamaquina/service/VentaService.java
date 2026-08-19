package com.sodalamaquina.service;
import com.sodalamaquina.domain.*;
import com.sodalamaquina.dto.CarritoItem;
import com.sodalamaquina.repository.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VentaService {
    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;
    private final RecetaRepository recetaRepository;
    private final InsumoRepository insumoRepository;

    public VentaService(VentaRepository ventaRepository,
            ProductoRepository productoRepository,
            RecetaRepository recetaRepository,
            InsumoRepository insumoRepository) {
        this.ventaRepository = ventaRepository;
        this.productoRepository = productoRepository;
        this.recetaRepository = recetaRepository;
        this.insumoRepository = insumoRepository;
    }

   
    @Transactional
    public Venta registrarVenta(Usuario usuario, List<CarritoItem> carrito) {
        Venta venta = new Venta();
        venta.setUsuario(usuario);
        venta.setFechaVenta(LocalDateTime.now());

        double total = 0.0;

        for (CarritoItem item : carrito) {
            Producto producto = productoRepository.findById(item.getIdProducto()).orElse(null);
            if (producto == null) {
                continue;
            }

            DetalleVenta detalle = new DetalleVenta();
            detalle.setVenta(venta);
            detalle.setProducto(producto);
            detalle.setCantidad(item.getCantidad());
            detalle.setSubtotal(item.getSubtotal());
            venta.getDetalles().add(detalle);

            total += item.getSubtotal();

            
            descontarInventario(producto, item.getCantidad());
        }

        venta.setTotalVenta(total);
        return ventaRepository.save(venta);
    }

    private void descontarInventario(Producto producto, int cantidadVendida) {
        Optional<Receta> recetaOpt = recetaRepository.findByProducto_IdProducto(producto.getIdProducto());
        if (recetaOpt.isEmpty()) {
            return; 
        }

        for (DetalleReceta detalleReceta : recetaOpt.get().getDetalles()) {
            Insumo insumo = detalleReceta.getInsumo();
            double aDescontar = detalleReceta.getCantidadUtilizada() * cantidadVendida;
            double nuevoStock = insumo.getCantidadActual() - aDescontar;

            if (nuevoStock < 0) {
                throw new IllegalStateException("Stock insuficiente de '" + insumo.getNombre()
                        + "' para vender " + cantidadVendida + " x " + producto.getNombreProducto());
            }

            insumo.setCantidadActual(nuevoStock);
            insumoRepository.save(insumo);
        }
    }

    @Transactional(readOnly = true)
    public Venta getVenta(Integer idVenta) {
        return ventaRepository.findById(idVenta).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Venta> getVentasEntreFechas(LocalDateTime inicio, LocalDateTime fin) {
        return ventaRepository.findByFechaVentaBetween(inicio, fin);
    }
}