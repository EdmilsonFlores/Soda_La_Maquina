/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sodalamaquina.ventas.dto;
import com.sodalamaquina.ventas.model.Receta;
import com.sodalamaquina.ventas.model.DetalleReceta;
import lombok.Data;
import java.util.List;
/**
 *
 * @author HP
 */
@Data
public class RecetaRequest {
    private Receta receta;
    private List<DetalleReceta> ingredientes;
}
