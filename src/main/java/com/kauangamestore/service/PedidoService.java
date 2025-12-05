//Classe de Kauan Batista Silveira

package com.kauangamestore.service;

import com.kauangamestore.dto.PedidoDTO;
import com.kauangamestore.dto.PedidoResponseDTO;
import java.util.List;

public interface PedidoService {
    PedidoResponseDTO realizarPedido(PedidoDTO dto, String emailUsuario);
    List<PedidoResponseDTO> meusPedidos(String emailUsuario);
}
