//Classe de Kauan Batista Silveira

package com.kauangamestore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EmpresaDTO(

    @NotBlank(message = "O nome da empresa deve ser informado.")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.")
    String nome,

    @NotBlank(message = "O país de origem deve ser informado.")
    String paisOrigem,

    @Size(max = 255, message = "A descrição não pode ultrapassar 255 caracteres.")
    String descricao
) {}
