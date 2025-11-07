//Classe de Kauan Batista Silveira

package com.kauangamestore.service;

import com.kauangamestore.dto.CategoriaDTO;
import com.kauangamestore.dto.CategoriaDTOResponse;
import java.util.List;

public interface CategoriaService {

    CategoriaDTOResponse create(CategoriaDTO dto);

    List<CategoriaDTOResponse> findAll();

    CategoriaDTOResponse findById(Long id);

    CategoriaDTOResponse update(Long id, CategoriaDTO dto);

    void delete(Long id);
}
