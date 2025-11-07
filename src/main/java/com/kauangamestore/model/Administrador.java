//Classe do aluno Kauan Batista Silveira

package com.kauangamestore.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ADMINISTRADOR")
public class Administrador extends Usuario {

    private String setorResponsavel;

    public Administrador() {}

    public Administrador(String nome, String email, String senha, String setorResponsavel) {
        super(nome, email, senha);
        this.setorResponsavel = setorResponsavel;
    }

    public String getSetorResponsavel() { return setorResponsavel; }
    public void setSetorResponsavel(String setorResponsavel) { this.setorResponsavel = setorResponsavel; }
}
