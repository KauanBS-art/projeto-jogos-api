//Classe de Kauan Batista

package com.kauangamestore.dto;

import com.kauangamestore.model.ItemPedido;

public record ItemPedidoResponseDTO(
    String tituloJogo,
    Integer quantidade,
    Double precoUnitario
) {
    public static ItemPedidoResponseDTO valueOf(ItemPedido item) {
        return new ItemPedidoResponseDTO(
            item.getJogo().getTitulo(),
            item.getQuantidade(),
            item.getPrecoUnitario()
        );
    }
}
