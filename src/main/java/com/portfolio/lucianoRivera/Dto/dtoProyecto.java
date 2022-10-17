package com.portfolio.lucianoRivera.Dto;

import javax.validation.constraints.NotBlank;


public class dtoProyecto {
    @NotBlank
    private String nombreProy;
    
    @NotBlank
    private String descripcionProy;

    public dtoProyecto() {
    }
    
    public dtoProyecto(String nombreProy, String descripcionProy) {
        this.nombreProy = nombreProy;
        this.descripcionProy = descripcionProy;
    }

    public String getNombreProy() {
        return nombreProy;
    }

    public void setNombreProy(String nombreProy) {
        this.nombreProy = nombreProy;
    }

    public String getDescripcionProy() {
        return descripcionProy;
    }

    public void setDescripcionProy(String descripcionProy) {
        this.descripcionProy = descripcionProy;
    }
}
