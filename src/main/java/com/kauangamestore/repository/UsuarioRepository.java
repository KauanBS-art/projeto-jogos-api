//Classe de Kauan Batista Silveira

package com.kauangamestore.repository;

import com.kauangamestore.model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {

    public Usuario findByEmail(String email) {
        return find("email", email).firstResult();
    }

    public Usuario findByEmailAndSenha(String email, String senha) {
        return find("email = ?1 and senha = ?2", email, senha).firstResult();
    }
}
