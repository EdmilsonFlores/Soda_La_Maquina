package com.sodalamaquina.repository;
import com.sodalamaquina.domain.DetalleVenta;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Integer> {
    public List<DetalleVenta> findByVenta_FechaVentaBetween(LocalDateTime inicio, LocalDateTime fin);
}