package com.kauangamestore.dto;

import java.time.LocalDate;

public record JogoDTOResponse(
    Long id,
    String titulo,
    String descricao,
    Double preco,
    LocalDate dataLancamento,
    String nomeEmpresa
) {}
