//Classe de Kauan Batista Silveira

package com.kauangamestore.dto;

import com.kauangamestore.model.Perfil;
import com.kauangamestore.model.Usuario;

public record UsuarioDTOResponse(
    Long id,
    String nome,
    String email,
    Perfil perfil
) {
    public static UsuarioDTOResponse valueOf(Usuario usuario) {
        return new UsuarioDTOResponse(
            usuario.getId(),
            usuario.getNome(),
            usuario.getEmail(),
            usuario.getPerfil()
        );
    }
}
