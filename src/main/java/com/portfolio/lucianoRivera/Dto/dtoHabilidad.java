package com.portfolio.lucianoRivera.Dto;

import javax.validation.constraints.NotBlank;


public class dtoHabilidad {
    @NotBlank
    private String nombreHab;
    
    @NotBlank
    private int porcentajeHab;

    public dtoHabilidad() {
    }
    
    public dtoHabilidad(String nombreHab, int porcentajeHab) {
        this.nombreHab = nombreHab;
        this.porcentajeHab = porcentajeHab;
    }

    public String getNombreHab() {
        return nombreHab;
    }

    public void setNombreHab(String nombreHab) {
        this.nombreHab = nombreHab;
    }

    public int getPorcentajeHab() {
        return porcentajeHab;
    }

    public void setPorcentajeHab(int porcentajeHab) {
        this.porcentajeHab = porcentajeHab;
    }
}
