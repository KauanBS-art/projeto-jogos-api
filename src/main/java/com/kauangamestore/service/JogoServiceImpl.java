package com.kauangamestore.service;

import com.kauangamestore.dto.JogoDTO;
import com.kauangamestore.dto.JogoDTOResponse;
import com.kauangamestore.model.Empresa;
import com.kauangamestore.model.Jogo;
import com.kauangamestore.model.Plataforma;
import com.kauangamestore.repository.EmpresaRepository;
import com.kauangamestore.repository.JogoRepository;
import com.kauangamestore.repository.PlataformaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class JogoServiceImpl implements JogoService {

    @Inject
    JogoRepository jogoRepository;

    @Inject
    EmpresaRepository empresaRepository;

    @Inject
    PlataformaRepository plataformaRepository;

    // ATENÇÃO: NÃO coloque o método 'toResponse' aqui. Use o valueOf do DTO.

    @Override
    @Transactional
    public JogoDTOResponse create(JogoDTO dto) {
        Empresa empresa = empresaRepository.findById(dto.idEmpresa());
        if (empresa == null) throw new NotFoundException("Empresa não encontrada");

        Jogo jogo = new Jogo();
        jogo.setTitulo(dto.titulo());
        jogo.setDescricao(dto.descricao());
        jogo.setPreco(dto.preco());
        jogo.setEstoque(dto.estoque());
        jogo.setDataLancamento(dto.dataLancamento());
        jogo.setDesenvolvedora(empresa);

        if (dto.idPlataformas() != null && !dto.idPlataformas().isEmpty()) {
            List<Plataforma> plataformas = new ArrayList<>();
            for (Long idPlat : dto.idPlataformas()) {
                Plataforma p = plataformaRepository.findById(idPlat);
                if (p != null) plataformas.add(p);
            }
            jogo.setPlataformas(plataformas);
        }

        jogoRepository.persist(jogo);
        return JogoDTOResponse.valueOf(jogo);
    }

    @Override
    public List<JogoDTOResponse> findAll() {
        return jogoRepository.listAll().stream()
            .map(JogoDTOResponse::valueOf) // Referência ao método estático
            .toList();
    }

    @Override
    public JogoDTOResponse findById(Long id) {
        Jogo jogo = jogoRepository.findById(id);
        if (jogo == null) throw new NotFoundException("Jogo não encontrado");
        return JogoDTOResponse.valueOf(jogo);
    }

    @Override
    @Transactional
    public JogoDTOResponse update(Long id, JogoDTO dto) {
        Jogo jogo = jogoRepository.findById(id);
        if (jogo == null) throw new NotFoundException("Jogo não encontrado");

        Empresa empresa = empresaRepository.findById(dto.idEmpresa());
        if (empresa == null) throw new NotFoundException("Empresa não encontrada");

        jogo.setTitulo(dto.titulo());
        jogo.setDescricao(dto.descricao());
        jogo.setPreco(dto.preco());
        jogo.setEstoque(dto.estoque());
        jogo.setDataLancamento(dto.dataLancamento());
        jogo.setDesenvolvedora(empresa);

        if (dto.idPlataformas() != null) {
            List<Plataforma> plataformas = new ArrayList<>();
            for (Long idPlat : dto.idPlataformas()) {
                Plataforma p = plataformaRepository.findById(idPlat);
                if (p != null) plataformas.add(p);
            }
            jogo.setPlataformas(plataformas);
        }

        return JogoDTOResponse.valueOf(jogo);
    }

    @Override
    @Transactional
    public boolean deletar(Long id) {
        return jogoRepository.deleteById(id);
    }
}
