package com.sodalamaquina.service;

import com.sodalamaquina.domain.Rol;
import com.sodalamaquina.repository.RolRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public List<Rol> getRoles() {
        return rolRepository.findAll();
    }

    public Rol getRol(Rol rol) {
        if (rol == null || rol.getIdRol() == null) {
            return null;
        }
        return rolRepository.findById(rol.getIdRol()).orElse(null);
    }

    public void save(Rol rol) {
        rolRepository.save(rol);
    }

    public void delete(Rol rol) {
        rolRepository.delete(rol);
    }
}