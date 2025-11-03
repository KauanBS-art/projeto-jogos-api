//Classe de Kauan Batista Silveira

package com.kauangamestore.service;

import com.kauangamestore.dto.CategoriaDTO;
import com.kauangamestore.dto.CategoriaDTOResponse;
import com.kauangamestore.model.Categoria;
import com.kauangamestore.repository.CategoriaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CategoriaServiceImpl implements CategoriaService {

    @Inject
    CategoriaRepository repository;

    private CategoriaDTOResponse toResponse(Categoria categoria) {
        int qtdJogos = categoria.getJogos() != null ? categoria.getJogos().size() : 0;
        return new CategoriaDTOResponse(categoria.getId(), categoria.getNome(), qtdJogos);
    }

    @Override
    @Transactional
    public CategoriaDTOResponse create(CategoriaDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setNome(dto.nome()); // ✅ corrigido
        repository.persist(categoria);
        return toResponse(categoria);
    }

    @Override
    public List<CategoriaDTOResponse> findAll() {
        return repository.listAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CategoriaDTOResponse findById(Long id) {
        Categoria categoria = repository.findById(id);
        return categoria != null ? toResponse(categoria) : null;
    }

    @Override
    @Transactional
    public CategoriaDTOResponse update(Long id, CategoriaDTO dto) {
        Categoria categoria = repository.findById(id);
        if (categoria == null) return null;
        categoria.setNome(dto.nome()); // ✅ corrigido
        repository.persist(categoria);
        return toResponse(categoria);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}