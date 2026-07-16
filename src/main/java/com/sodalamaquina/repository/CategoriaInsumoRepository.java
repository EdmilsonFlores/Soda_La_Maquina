package com.sodalamaquina.repository;

import com.sodalamaquina.domain.CategoriaInsumo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaInsumoRepository
        extends JpaRepository<CategoriaInsumo, Integer> {

    public List<CategoriaInsumo> findByActivoTrue();
}