//Classe de Kauan Batista Silveira

package com.kauangamestore.repository;

import com.kauangamestore.model.Categoria;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CategoriaRepository implements PanacheRepository<Categoria> {

    public Categoria findByNome(String nome) {
        return find("nome", nome).firstResult();
    }
}
