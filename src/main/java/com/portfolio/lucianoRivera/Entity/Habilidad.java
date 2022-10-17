package com.portfolio.lucianoRivera.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Habilidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombreHab;
    private int porcentajeHab;

    public Habilidad() {
    }

    public Habilidad(String nombreHab, int porcentajeHab) {
        this.nombreHab = nombreHab;
        this.porcentajeHab = porcentajeHab;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
