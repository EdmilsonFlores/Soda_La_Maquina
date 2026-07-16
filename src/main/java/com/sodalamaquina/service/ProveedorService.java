package com.sodalamaquina.service;

import com.sodalamaquina.domain.Proveedor;
import com.sodalamaquina.repository.ProveedorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    public List<Proveedor> getProveedores() {
        return proveedorRepository.findAll();
    }

    public Proveedor getProveedor(Proveedor proveedor) {
        if (proveedor == null || proveedor.getIdProveedor() == null) {
            return null;
        }
        return proveedorRepository.findById(proveedor.getIdProveedor()).orElse(null);
    }

    public void save(Proveedor proveedor) {
        proveedorRepository.save(proveedor);
    }

    public void delete(Proveedor proveedor) {
        proveedorRepository.delete(proveedor);
    }
}
