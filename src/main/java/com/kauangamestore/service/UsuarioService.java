//Classe de Kauan Batista Silveira

package com.kauangamestore.service;

import com.kauangamestore.dto.UsuarioDTO;
import com.kauangamestore.dto.UsuarioDTOResponse;
import java.util.List;

public interface UsuarioService {

    UsuarioDTOResponse create(UsuarioDTO dto);
    List<UsuarioDTOResponse> findAll();
    UsuarioDTOResponse findById(Long id);
    UsuarioDTOResponse update(Long id, UsuarioDTO dto);
    boolean deletar(Long id);
}
