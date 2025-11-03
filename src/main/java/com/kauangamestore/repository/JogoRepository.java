package com.kauangamestore.repository;

import com.kauangamestore.model.Jogo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JogoRepository implements PanacheRepository<Jogo> {
  
}
