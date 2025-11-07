//Classe de Kauan Batista Silveira

package com.kauangamestore.service;

import com.kauangamestore.dto.EmpresaDTO;
import com.kauangamestore.dto.EmpresaDTOResponse;
import com.kauangamestore.model.Empresa;
import com.kauangamestore.repository.EmpresaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class EmpresaServiceImpl implements EmpresaService {

    @Inject
    EmpresaRepository repository;

    @Override
    @Transactional
    public EmpresaDTOResponse create(EmpresaDTO dto) {
        if (dto == null) return null;

        Empresa empresa = new Empresa();
        empresa.setNome(dto.nome());
        empresa.setPaisOrigem(dto.paisOrigem());
        empresa.setDescricao(dto.descricao() != null ? dto.descricao() : "");
        repository.persist(empresa);

        return new EmpresaDTOResponse(
            empresa.getId(),
            empresa.getNome(),
            empresa.getPaisOrigem(),
            empresa.getDescricao(),
            0
        );
    }

    @Override
    public List<EmpresaDTOResponse> findAll() {
        return repository.listAll().stream()
            .map(empresa -> new EmpresaDTOResponse(
                empresa.getId(),
                empresa.getNome(),
                empresa.getPaisOrigem(),
                empresa.getDescricao(),
                empresa.getJogos() != null ? empresa.getJogos().size() : 0
            ))
            .collect(Collectors.toList());
    }

    @Override
    public EmpresaDTOResponse findById(Long id) {
        Empresa empresa = repository.findById(id);
        if (empresa == null) {
            return null;
        }
        return new EmpresaDTOResponse(
            empresa.getId(),
            empresa.getNome(),
            empresa.getPaisOrigem(),
            empresa.getDescricao(),
            empresa.getJogos() != null ? empresa.getJogos().size() : 0
        );
    }

    @Override
    @Transactional
    public EmpresaDTOResponse update(Long id, EmpresaDTO dto) {
        Empresa empresa = repository.findById(id);
        if (empresa == null) {
            return null;
        }
        empresa.setNome(dto.nome());
        empresa.setPaisOrigem(dto.paisOrigem());
        empresa.setDescricao(dto.descricao() != null ? dto.descricao() : "");
        
        return new EmpresaDTOResponse(
            empresa.getId(),
            empresa.getNome(),
            empresa.getPaisOrigem(),
            empresa.getDescricao(),
            empresa.getJogos() != null ? empresa.getJogos().size() : 0
        );
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Empresa empresa = repository.findById(id);
        if (empresa != null) {
            repository.delete(empresa);
        }
    }
}
