//Classe de Kauan Batista Silveira

package com.kauangamestore.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonFormat;

public record JogoDTO(

    @NotBlank(message = "O título do jogo deve ser informado.")
    @Size(max = 120, message = "O título pode ter no máximo 120 caracteres.")
    String titulo,

    @NotBlank(message = "A descrição do jogo deve ser informada.")
    @Size(max = 255, message = "A descrição pode ter no máximo 255 caracteres.")
    String descricao,

    @NotNull(message = "O preço deve ser informado.")
    @Positive(message = "O preço deve ser maior que zero.")
    Double preco,

    @Schema(type = SchemaType.STRING, example = "09/08/2005")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "A data de lançamento deve ser informada.")
    LocalDate dataLancamento,

    @NotNull(message = "A empresa do jogo deve ser informada (ID).")
    Long idEmpresa
) {}
