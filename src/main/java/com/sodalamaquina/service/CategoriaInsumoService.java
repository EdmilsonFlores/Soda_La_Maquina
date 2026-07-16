package com.sodalamaquina.service;

import com.sodalamaquina.domain.CategoriaInsumo;
import com.sodalamaquina.repository.CategoriaInsumoRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaInsumoService {

    private final CategoriaInsumoRepository categoriaRepository;

    public CategoriaInsumoService(CategoriaInsumoRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional(readOnly = true)
    public List<CategoriaInsumo> getCategorias(boolean activo) {

        if (activo) {
            return categoriaRepository.findByActivoTrue();
        }

        return categoriaRepository.findAll();   
    }
    
    @Transactional
    public void save(CategoriaInsumo categoria) {
    categoriaRepository.save(categoria);
    }
    
    @Transactional(readOnly = true)
    public CategoriaInsumo getCategoria(Integer idCategoria) {
    return categoriaRepository.findById(idCategoria).orElse(null);
    }
    
    @Transactional
    public void delete(Integer idCategoria) {
    categoriaRepository.deleteById(idCategoria);
    }   
    
    
    
}