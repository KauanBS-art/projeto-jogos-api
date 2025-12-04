package com.kauangamestore.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public record PedidoDTO(
    @NotNull(message = "A lista de itens não pode ser nula")
    List<ItemPedidoDTO> itens,

    @NotNull(message = "O modo de pagamento é obrigatório (1=PIX, 2=BOLETO)")
    Integer idModoPagamento,

    String cep,
    String logradouro,
    String numero,
    String cidade
) { }
