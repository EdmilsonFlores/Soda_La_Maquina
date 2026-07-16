package com.sodalamaquina.service;

import com.sodalamaquina.domain.Usuario;
import com.sodalamaquina.repository.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuario(Usuario usuario) {
        if (usuario == null || usuario.getIdUsuario() == null) {
            return null;
        }
        return usuarioRepository.findById(usuario.getIdUsuario()).orElse(null);
    }

    public void save(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public void delete(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }
}
