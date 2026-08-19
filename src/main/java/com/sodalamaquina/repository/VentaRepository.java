package com.sodalamaquina.repository;

import com.sodalamaquina.domain.Venta;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VentaRepository extends JpaRepository<Venta, Integer> {
    public List<Venta> findByFechaVentaBetween(LocalDateTime inicio, LocalDateTime fin);
}