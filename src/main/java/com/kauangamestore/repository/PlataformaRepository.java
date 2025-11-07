package com.kauangamestore.repository;

import com.kauangamestore.model.Plataforma;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlataformaRepository implements PanacheRepository<Plataforma> {

    public Plataforma findByNome(String nome) {
        return find("nome", nome).firstResult();
    }
}
