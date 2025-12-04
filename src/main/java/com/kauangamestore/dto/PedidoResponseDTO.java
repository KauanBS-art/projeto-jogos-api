package com.kauangamestore.dto;

import com.kauangamestore.model.Pedido;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponseDTO(
    Long id,
    LocalDateTime data,
    Double total,
    String modoPagamento,
    List<ItemPedidoResponseDTO> itens
) {
    public static PedidoResponseDTO valueOf(Pedido pedido) {
        String tipoPagamento = pedido.getPagamento().getClass().getSimpleName()
                                .replace("Pagamento", "").toUpperCase();
        
        return new PedidoResponseDTO(
            pedido.getId(),
            pedido.getDataPedido(),
            pedido.getValorTotal(),
            tipoPagamento,
            pedido.getItens().stream().map(ItemPedidoResponseDTO::valueOf).toList()
        );
    }
}
