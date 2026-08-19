package com.sodalamaquina.repository;

import com.sodalamaquina.domain.Receta;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecetaRepository extends JpaRepository<Receta, Integer> {
    public Optional<Receta> findByProducto_IdProducto(Integer idProducto);
}