package com.sodalamaquina.dto;

public class InsumoConsumidoDTO {
    private Integer idInsumo;
    private String nombreInsumo;
    private String unidadMedida;
    private Double cantidadConsumida;

    public InsumoConsumidoDTO(Integer idInsumo, String nombreInsumo, String unidadMedida, Double cantidadConsumida) {
        this.idInsumo = idInsumo;
        this.nombreInsumo = nombreInsumo;
        this.unidadMedida = unidadMedida;
        this.cantidadConsumida = cantidadConsumida;
    }

    public Integer getIdInsumo() {
        return idInsumo;
    }
    public String getNombreInsumo() {
        return nombreInsumo;
    }
    public String getUnidadMedida() {
        return unidadMedida;
    }
    public Double getCantidadConsumida() {
        return cantidadConsumida;
    }
    public void setCantidadConsumida(Double cantidadConsumida) {
        this.cantidadConsumida = cantidadConsumida;
    }
}