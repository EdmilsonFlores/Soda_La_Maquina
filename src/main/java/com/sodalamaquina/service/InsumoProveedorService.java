package com.sodalamaquina.service;


import com.sodalamaquina.domain.InsumoProveedor;
import com.sodalamaquina.repository.InsumoProveedorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsumoProveedorService {

    @Autowired
    private InsumoProveedorRepository insumoProveedorRepository;

    public List<InsumoProveedor> getInsumosProveedor() {
        return insumoProveedorRepository.findAll();
    }

    public InsumoProveedor getInsumoProveedor(InsumoProveedor insumoProveedor) {
        if (insumoProveedor == null || insumoProveedor.getIdInsumoProveedor() == null) {
            return null;
        }
        return insumoProveedorRepository.findById(insumoProveedor.getIdInsumoProveedor()).orElse(null);
    }

    public void save(InsumoProveedor insumoProveedor) {
        insumoProveedorRepository.save(insumoProveedor);
    }

    public void delete(InsumoProveedor insumoProveedor) {
        insumoProveedorRepository.delete(insumoProveedor);
    }
}
