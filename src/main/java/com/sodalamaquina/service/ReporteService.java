package com.sodalamaquina.service;
import com.sodalamaquina.domain.DetalleReceta;
import com.sodalamaquina.domain.DetalleVenta;
import com.sodalamaquina.domain.Receta;
import com.sodalamaquina.domain.Venta;
import com.sodalamaquina.dto.InsumoConsumidoDTO;
import com.sodalamaquina.repository.DetalleVentaRepository;
import com.sodalamaquina.repository.RecetaRepository;
import com.sodalamaquina.repository.VentaRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReporteService {
    private final VentaRepository ventaRepository;
    private final DetalleVentaRepository detalleVentaRepository;
    private final RecetaRepository recetaRepository;

    public ReporteService(VentaRepository ventaRepository,
            DetalleVentaRepository detalleVentaRepository,
            RecetaRepository recetaRepository) {
        this.ventaRepository = ventaRepository;
        this.detalleVentaRepository = detalleVentaRepository;
        this.recetaRepository = recetaRepository;
    }


    @Transactional(readOnly = true)
    public double totalVentas(LocalDate inicio, LocalDate fin) {
        return ventaRepository.findByFechaVentaBetween(inicio.atStartOfDay(), fin.atTime(LocalTime.MAX))
                .stream().mapToDouble(Venta::getTotalVenta).sum();
    }

    @Transactional(readOnly = true)
    public long cantidadTransacciones(LocalDate inicio, LocalDate fin) {
        return ventaRepository.findByFechaVentaBetween(inicio.atStartOfDay(), fin.atTime(LocalTime.MAX)).size();
    }

   
    @Transactional(readOnly = true)
    public List<InsumoConsumidoDTO> topInsumos(LocalDate inicio, LocalDate fin, int top) {
        List<DetalleVenta> detalles = detalleVentaRepository.findByVenta_FechaVentaBetween(
                inicio.atStartOfDay(), fin.atTime(LocalTime.MAX));

        Map<Integer, InsumoConsumidoDTO> acumulado = new HashMap<>();

        for (DetalleVenta detalleVenta : detalles) {
            Integer idProducto = detalleVenta.getProducto().getIdProducto();
            Optional<Receta> recetaOpt = recetaRepository.findByProducto_IdProducto(idProducto);
            if (recetaOpt.isEmpty()) {
                continue;
            }

            for (DetalleReceta detalleReceta : recetaOpt.get().getDetalles()) {
                Integer idInsumo = detalleReceta.getInsumo().getIdInsumo();
                double consumo = detalleReceta.getCantidadUtilizada() * detalleVenta.getCantidad();

                acumulado.merge(idInsumo,
                        new InsumoConsumidoDTO(idInsumo, detalleReceta.getInsumo().getNombre(),
                                detalleReceta.getInsumo().getUnidadMedida(), consumo),
                        (a, b) -> {
                            a.setCantidadConsumida(a.getCantidadConsumida() + b.getCantidadConsumida());
                            return a;
                        });
            }
        }

        return acumulado.values().stream()
                .sorted(Comparator.comparingDouble(InsumoConsumidoDTO::getCantidadConsumida).reversed())
                .limit(top)
                .collect(Collectors.toList());
    }
}