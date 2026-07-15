/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sodalamaquina.ventas.repository;
import com.sodalamaquina.ventas.model.DetalleReceta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
/**
 *
 * @author HP
 */
public interface DetalleRecetaRepository extends JpaRepository<DetalleReceta, Long> {
    List<DetalleReceta> findByRecetaIdReceta(Long idReceta);
}
