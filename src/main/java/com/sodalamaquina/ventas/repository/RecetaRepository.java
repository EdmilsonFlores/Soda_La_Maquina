/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sodalamaquina.ventas.repository;
import com.sodalamaquina.ventas.model.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
/**
 *
 * @author HP
 */
public interface RecetaRepository extends JpaRepository<Receta, Long>{
    Optional<Receta> findByProductoIdProducto(Long idProducto);
}
