package com.sodalamaquina.repository;

import com.sodalamaquina.domain.Insumo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsumoRepository
        extends JpaRepository<Insumo, Integer> {

    public List<Insumo> findByActivoTrue();
}