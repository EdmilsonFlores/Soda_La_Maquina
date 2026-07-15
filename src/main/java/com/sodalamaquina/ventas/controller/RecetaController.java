/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sodalamaquina.ventas.controller;
import com.sodalamaquina.ventas.model.Receta;
import com.sodalamaquina.ventas.model.DetalleReceta;
import com.sodalamaquina.ventas.repository.RecetaRepository;
import com.sodalamaquina.ventas.repository.DetalleRecetaRepository;
import com.sodalamaquina.ventas.dto.RecetaRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 *
 * @author HP
 */
@RestController
@RequestMapping("/api/recetas")
public class RecetaController {
    private final RecetaRepository recetaRepository;
    private final DetalleRecetaRepository detalleRecetaRepository;

    public RecetaController(RecetaRepository recetaRepository,
                             DetalleRecetaRepository detalleRecetaRepository) {
        this.recetaRepository = recetaRepository;
        this.detalleRecetaRepository = detalleRecetaRepository;
    }


    @PostMapping
    public Receta crearReceta(@RequestBody RecetaRequest request) {
        Receta receta = recetaRepository.save(request.getReceta());

        for (DetalleReceta detalle : request.getIngredientes()) {
            detalle.setReceta(receta);
            detalleRecetaRepository.save(detalle);
        }
        return receta;
    }

    
    @GetMapping("/producto/{idProducto}")
    public List<DetalleReceta> verIngredientes(@PathVariable Long idProducto) {
        Receta receta = recetaRepository.findByProductoIdProducto(idProducto)
                .orElseThrow(() -> new RuntimeException("No existe receta para este producto"));
        return detalleRecetaRepository.findByRecetaIdReceta(receta.getIdReceta());
    }
}
