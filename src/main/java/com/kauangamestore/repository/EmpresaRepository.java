package com.kauangamestore.repository;

import com.kauangamestore.model.Empresa;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmpresaRepository implements PanacheRepository<Empresa> {

    public Empresa findByNome(String nome) {
        return find("nome", nome).firstResult();
    }
}
