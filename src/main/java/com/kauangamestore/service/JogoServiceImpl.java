//Classe de Kauan Batista Silveira

package com.kauangamestore.service;

import com.kauangamestore.dto.JogoDTO;
import com.kauangamestore.dto.JogoDTOResponse;
import com.kauangamestore.model.Empresa;
import com.kauangamestore.model.Jogo;
import com.kauangamestore.repository.EmpresaRepository;
import com.kauangamestore.repository.JogoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class JogoServiceImpl implements JogoService {

    @Inject
    JogoRepository jogoRepository;

    @Inject
    EmpresaRepository empresaRepository;

    private JogoDTOResponse toResponse(Jogo jogo) {
        return new JogoDTOResponse(
            jogo.getId(),
            jogo.getTitulo(),
            jogo.getDescricao(),
            jogo.getPreco(),
            jogo.getDataLancamento(),
            jogo.getEmpresa() != null ? jogo.getEmpresa().getNome() : null
        );
    }

    @Override
    @Transactional
    public JogoDTOResponse create(JogoDTO dto) {
        if (dto == null || dto.idEmpresa() == null)
            return null;

        Empresa empresa = empresaRepository.findById(dto.idEmpresa());
        if (empresa == null) {
            // Não lança exceção, apenas retorna nulo para o Resource retornar 400
            return null;
        }

        Jogo jogo = new Jogo();
        jogo.setTitulo(dto.titulo());
        jogo.setDescricao(dto.descricao());
        jogo.setPreco(dto.preco());
        jogo.setDataLancamento(dto.dataLancamento());
        jogo.setEmpresa(empresa);

        jogoRepository.persist(jogo);
        return toResponse(jogo);
    }

    @Override
    public List<JogoDTOResponse> findAll() {
        return jogoRepository.listAll()
            .stream()
            .map(this::toResponse)
            .collect(Collectors.toList());
    }

    @Override
    public JogoDTOResponse findById(Long id) {
        Jogo jogo = jogoRepository.findById(id);
        return jogo != null ? toResponse(jogo) : null;
    }

    @Override
    @Transactional
    public JogoDTOResponse update(Long id, JogoDTO dto) {
        Jogo jogo = jogoRepository.findById(id);
        if (jogo == null) return null;

        Empresa empresa = empresaRepository.findById(dto.idEmpresa());
        if (empresa == null) return null;

        jogo.setTitulo(dto.titulo());
        jogo.setDescricao(dto.descricao());
        jogo.setPreco(dto.preco());
        jogo.setDataLancamento(dto.dataLancamento());
        jogo.setEmpresa(empresa);

        return toResponse(jogo);
    }

    @Override
    @Transactional
    public boolean deletar(Long id) {
        return jogoRepository.deleteById(id);
    }
}
