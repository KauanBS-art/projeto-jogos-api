package com.kauangamestore.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("MODERADOR")
public class Moderador extends Usuario {

    private String areaMonitorada;

    public Moderador() {}

    public Moderador(String nome, String email, String senha, String areaMonitorada) {
        super(nome, email, senha);
        this.areaMonitorada = areaMonitorada;
    }

    public String getAreaMonitorada() { return areaMonitorada; }
    public void setAreaMonitorada(String areaMonitorada) { this.areaMonitorada = areaMonitorada; }
}
