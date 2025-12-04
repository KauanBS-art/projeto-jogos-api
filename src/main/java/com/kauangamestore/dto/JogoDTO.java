//Classe de Kauan Batista Silveira

package com.kauangamestore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;

public record JogoDTO(
    @NotBlank(message = "Título é obrigatório")
    String titulo,

    String descricao,

    @NotNull(message = "Preço é obrigatório")
    @Positive
    Double preco,

    @NotNull(message = "Estoque é obrigatório")
    @Positive
    Integer estoque, // CAMPO NOVO IMPORTANTE

    LocalDate dataLancamento,

    @NotNull(message = "Empresa é obrigatória")
    Long idEmpresa,

    // Relacionamento N:N
    List<Long> idPlataformas
) { }
