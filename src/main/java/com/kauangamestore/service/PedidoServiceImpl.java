package com.kauangamestore.service;

import com.kauangamestore.dto.ItemPedidoDTO;
import com.kauangamestore.dto.PedidoDTO;
import com.kauangamestore.dto.PedidoResponseDTO;
import com.kauangamestore.model.*;
import com.kauangamestore.repository.JogoRepository;
import com.kauangamestore.repository.PedidoRepository;
import com.kauangamestore.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import org.jboss.logging.Logger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService {

    private static final Logger LOG = Logger.getLogger(PedidoServiceImpl.class);

    @Inject
    PedidoRepository pedidoRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    JogoRepository jogoRepository;

    @Override
    @Transactional
    public PedidoResponseDTO realizarPedido(PedidoDTO dto, String emailUsuario) {
        LOG.info("Iniciando pedido para o usuario: " + emailUsuario);

        Usuario usuario = usuarioRepository.findByEmail(emailUsuario);
        if (usuario == null) {
            LOG.error("Usuario nao encontrado: " + emailUsuario);
            throw new NotFoundException("Usuário não encontrado");
        }

        Pedido pedido = new Pedido();
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setUsuario(usuario);

        Endereco endereco = new Endereco();
        endereco.cep = dto.cep();
        endereco.logradouro = dto.logradouro();
        endereco.numero = dto.numero();
        endereco.cidade = dto.cidade();
        pedido.setEnderecoEntrega(endereco);

        List<ItemPedido> itens = new ArrayList<>();
        Double total = 0.0;

        for (ItemPedidoDTO itemDTO : dto.itens()) {
            Jogo jogo = jogoRepository.findById(itemDTO.idJogo());
            if (jogo == null) 
                throw new NotFoundException("Jogo não encontrado (ID: " + itemDTO.idJogo() + ")");

            if (jogo.getEstoque() < itemDTO.quantidade()) {
                throw new BadRequestException("Estoque insuficiente: " + jogo.getTitulo());
            }
            
            jogo.setEstoque(jogo.getEstoque() - itemDTO.quantidade());

            ItemPedido item = new ItemPedido();
            item.setQuantidade(itemDTO.quantidade());
            item.setPrecoUnitario(jogo.getPreco());
            item.setJogo(jogo);
            item.setPedido(pedido);

            itens.add(item);
            total += (jogo.getPreco() * itemDTO.quantidade());
        }

        pedido.setItens(itens);
        pedido.setValorTotal(total);

        Pagamento pagamento;
        if (dto.idModoPagamento() == 1) {
            PagamentoPix pix = new PagamentoPix();
            pix.setDataExpiracao(LocalDateTime.now().plusMinutes(30));
            pix.setChavePix(UUID.randomUUID().toString());
            pix.setCodigoTransacao(UUID.randomUUID().toString());
            pagamento = pix;
        } else {
            PagamentoBoleto boleto = new PagamentoBoleto();
            boleto.setDataVencimento(LocalDate.now().plusDays(3));
            boleto.setNumeroBoleto("34191.79001 " + System.currentTimeMillis());
            pagamento = boleto;
        }
        
        pagamento.setValor(total);
        pagamento.setDataConfirmacao(null);
        pedido.setPagamento(pagamento);

        pedidoRepository.persist(pedido);

        LOG.info("Pedido realizado com sucesso. ID: " + pedido.getId());
        return PedidoResponseDTO.valueOf(pedido);
    }

    @Override
    public List<PedidoResponseDTO> meusPedidos(String emailUsuario) {
        return pedidoRepository.list("usuario.email", emailUsuario).stream()
            .map(PedidoResponseDTO::valueOf)
            .toList();
    }
}
