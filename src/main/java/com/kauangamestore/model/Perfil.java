//Enum de Kauan Batista

package com.kauangamestore.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Perfil {
    ADMIN (1, "Administrador"),
    USER (2, "Cliente");

    private final Integer id;
    private final String label;

    Perfil(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() { return id; }
    public String getLabel() { return label; }

    public static Perfil valueOf(Integer id) {
        if (id == null) return null;
        for (Perfil p : Perfil.values()) {
            if (id.equals(p.getId())) return p;
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }
}
