//Classe de Kauan Batista Silveira

package com.kauangamestore.service;

import com.kauangamestore.dto.PlataformaDTO;
import com.kauangamestore.dto.PlataformaDTOResponse;
import com.kauangamestore.model.Plataforma;
import com.kauangamestore.repository.PlataformaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PlataformaServiceImpl implements PlataformaService {

    @Inject
    PlataformaRepository repository;

    private PlataformaDTOResponse toResponse(Plataforma plataforma) {
        int qtdJogos = plataforma.getJogos() != null ? plataforma.getJogos().size() : 0;
        return new PlataformaDTOResponse(plataforma.getId(), plataforma.getNome(), qtdJogos);
    }

    @Override
    @Transactional
    public PlataformaDTOResponse create(PlataformaDTO dto) {
        Plataforma plataforma = new Plataforma();
        plataforma.setNome(dto.nome()); // ✅ corrigido
        repository.persist(plataforma);
        return toResponse(plataforma);
    }

    @Override
    public List<PlataformaDTOResponse> findAll() {
        return repository.listAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PlataformaDTOResponse findById(Long id) {
        Plataforma plataforma = repository.findById(id);
        return plataforma != null ? toResponse(plataforma) : null;
    }

    @Override
    @Transactional
    public PlataformaDTOResponse update(Long id, PlataformaDTO dto) {
        Plataforma plataforma = repository.findById(id);
        if (plataforma == null) return null;
        plataforma.setNome(dto.nome()); // ✅ corrigido
        repository.persist(plataforma);
        return toResponse(plataforma);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}