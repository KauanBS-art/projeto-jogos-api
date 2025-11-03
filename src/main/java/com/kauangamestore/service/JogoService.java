//Classe de Kauan Batista Silveira

package com.kauangamestore.service;

import com.kauangamestore.dto.JogoDTO;
import com.kauangamestore.dto.JogoDTOResponse;
import java.util.List;

public interface JogoService {

    JogoDTOResponse create(JogoDTO dto);
    List<JogoDTOResponse> findAll();
    JogoDTOResponse findById(Long id);
    JogoDTOResponse update(Long id, JogoDTO dto);
    boolean deletar(Long id);
}
