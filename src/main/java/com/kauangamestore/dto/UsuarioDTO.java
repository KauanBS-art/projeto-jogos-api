//Classe de Kauan Batista Silveira

package com.kauangamestore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UsuarioDTO(

    @NotBlank(message = "O nome do usuário deve ser informado.")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.")
    String nome,

    @NotBlank(message = "O e-mail deve ser informado.")
    @Email(message = "O e-mail informado é inválido.")
    String email,

    @NotBlank(message = "A senha deve ser informada.")
    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres.")
    String senha
) {}
