package com.sodalamaquina.service;

import com.sodalamaquina.domain.Producto;
import com.sodalamaquina.repository.ProductoRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




@Service
public class ProductoService {
    private final ProductoRepository productoRepository;
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }
    
    @Transactional(readOnly = true)
    public List<Producto> getProductos(boolean activo) {
        if (activo) {
            return productoRepository.findByActivoTrue();
        }
        return productoRepository.findAll();
    }
    
    @Transactional
    public void save(Producto producto) {
        productoRepository.save(producto);
    }
    
    @Transactional(readOnly = true)
    public Producto getProducto(Integer idProducto) {
        return productoRepository.findById(idProducto).orElse(null);
    }
    
    @Transactional
    public void delete(Integer idProducto) {
        productoRepository.deleteById(idProducto);
    }
}