//Classe de Kauan Batista Silveira

package com.kauangamestore.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonFormat;

public record JogoDTO(

    @NotBlank(message = "O título do jogo deve ser informado.")
    String titulo,

    @NotBlank(message = "A descrição do jogo deve ser informada.")
    String descricao,

    @NotNull(message = "O preço deve ser informado.")
    @Positive(message = "O preço deve ser maior que zero.")
    Double preco,

    @Schema(type = SchemaType.STRING, example = "15/08/2020")
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dataLancamento,

    @NotNull(message = "A empresa do jogo deve ser informada (ID).")
    Long idEmpresa
) {}
