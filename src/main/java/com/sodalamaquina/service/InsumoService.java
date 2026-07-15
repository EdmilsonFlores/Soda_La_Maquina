package com.sodalamaquina.service;

import com.sodalamaquina.domain.Insumo;
import com.sodalamaquina.repository.InsumoRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InsumoService {

    private final InsumoRepository insumoRepository;

    public InsumoService(InsumoRepository insumoRepository) {
        this.insumoRepository = insumoRepository;
    }

    @Transactional(readOnly = true)
    public List<Insumo> getInsumos(boolean activo) {

        if (activo) {
            return insumoRepository.findByActivoTrue();
        }

        return insumoRepository.findAll();
    }

    @Transactional
    public void save(Insumo insumo) {
        insumoRepository.save(insumo);
    }

    @Transactional(readOnly = true)
    public Insumo getInsumo(Integer idInsumo) {
        return insumoRepository.findById(idInsumo).orElse(null);
    }

    @Transactional
    public void delete(Integer idInsumo) {
        insumoRepository.deleteById(idInsumo);
    }
}