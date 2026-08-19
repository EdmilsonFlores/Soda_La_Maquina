package com.sodalamaquina.service;

import com.sodalamaquina.domain.DetalleReceta;
import com.sodalamaquina.domain.Insumo;
import com.sodalamaquina.domain.Producto;
import com.sodalamaquina.domain.Receta;
import com.sodalamaquina.repository.InsumoRepository;
import com.sodalamaquina.repository.ProductoRepository;
import com.sodalamaquina.repository.RecetaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RecetaService {

    private final RecetaRepository recetaRepository;
    private final ProductoRepository productoRepository;
    private final InsumoRepository insumoRepository;

    public RecetaService(
            RecetaRepository recetaRepository,
            ProductoRepository productoRepository,
            InsumoRepository insumoRepository) {

        this.recetaRepository = recetaRepository;
        this.productoRepository = productoRepository;
        this.insumoRepository = insumoRepository;
    }

    @Transactional(readOnly = true)
    public Receta getRecetaPorProducto(Integer idProducto) {

        return recetaRepository
                .findByProducto_IdProducto(idProducto)
                .orElseGet(() -> {

                    Receta receta = new Receta();

                    Producto producto = productoRepository
                            .findById(idProducto)
                            .orElse(null);

                    receta.setProducto(producto);

                    return receta;
                });
    }

    @Transactional
    public void guardar(
            Integer idProducto,
            Integer[] idInsumo,
            Double[] cantidadUtilizada) {

        Producto producto = productoRepository
                .findById(idProducto)
                .orElse(null);

        if (producto == null) {
            return;
        }

        Receta receta = recetaRepository
                .findByProducto_IdProducto(idProducto)
                .orElseGet(Receta::new);

        receta.setProducto(producto);

        receta.getDetalles().clear();

        if (idInsumo != null) {

            for (int i = 0; i < idInsumo.length; i++) {

                if (cantidadUtilizada == null
                        || i >= cantidadUtilizada.length
                        || cantidadUtilizada[i] == null) {
                    continue;
                }

                Insumo insumo = insumoRepository
                        .findById(idInsumo[i])
                        .orElse(null);

                if (insumo == null) {
                    continue;
                }

                DetalleReceta detalle = new DetalleReceta();

                detalle.setReceta(receta);
                detalle.setInsumo(insumo);
                detalle.setCantidadUtilizada(
                        cantidadUtilizada[i]
                );

                receta.getDetalles().add(detalle);
            }
        }

        recetaRepository.save(receta);
    }
}