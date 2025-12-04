//Classe de Kauan Batista

package com.kauangamestore.dto;

import com.kauangamestore.model.Jogo;
import com.kauangamestore.model.Plataforma;
import java.time.LocalDate;
import java.util.List;

public record JogoDTOResponse(
    Long id,
    String titulo,
    String descricao,
    Double preco,
    Integer estoque,
    LocalDate dataLancamento,
    String nomeEmpresa,
    List<String> plataformas
) {
    public static JogoDTOResponse valueOf(Jogo jogo) {
        // Proteção contra nulos para evitar NullPointerException e erros de compilação
        List<String> listaPlataformas = (jogo.getPlataformas() != null) 
            ? jogo.getPlataformas().stream().map(Plataforma::getNome).toList() 
            : List.of();

        return new JogoDTOResponse(
            jogo.getId(),
            jogo.getTitulo(),
            jogo.getDescricao(),
            jogo.getPreco(),
            jogo.getEstoque(),
            jogo.getDataLancamento(),
            jogo.getDesenvolvedora() != null ? jogo.getDesenvolvedora().getNome() : null,
            listaPlataformas
        );
    }
}
