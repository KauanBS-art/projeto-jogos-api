package com.kauangamestore.service;

import com.kauangamestore.dto.EmpresaDTO;
import com.kauangamestore.dto.EmpresaDTOResponse;
import java.util.List;

public interface EmpresaService {

    EmpresaDTOResponse create(EmpresaDTO dto);

    List<EmpresaDTOResponse> findAll();

    EmpresaDTOResponse findById(Long id);

    EmpresaDTOResponse update(Long id, EmpresaDTO dto);

    void delete(Long id);
}
