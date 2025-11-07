//Classe de Kauan Batista Silveira

package com.kauangamestore.service;

import com.kauangamestore.dto.PlataformaDTO;
import com.kauangamestore.dto.PlataformaDTOResponse;
import java.util.List;

public interface PlataformaService {

    PlataformaDTOResponse create(PlataformaDTO dto);

    List<PlataformaDTOResponse> findAll();

    PlataformaDTOResponse findById(Long id);

    PlataformaDTOResponse update(Long id, PlataformaDTO dto);

    void delete(Long id);
}
