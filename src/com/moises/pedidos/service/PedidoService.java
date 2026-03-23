package com.moises.pedidos.service;

import com.moises.pedidos.core.CrudService;
import com.moises.pedidos.exception.PedidoNaoEncontradoException;
import com.moises.pedidos.model.ItemPedido;
import com.moises.pedidos.model.Pedido;
import com.moises.pedidos.model.enums.StatusPedido;
import com.moises.pedidos.repository.PedidoRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PedidoService implements CrudService<Pedido> {
    private PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public void cadastrar(Pedido pedido) throws Exception {
        if (pedido.getCliente() == null) {
            throw new Exception("ERRO: Pedido tem que estar vinculado a um cliente.");
        }
        if (pedido.getPrioridade() == null) {
            throw new Exception("ERRO: Você precisa definir a prioridade do pedido.");
        }
        // Agora o pedido pode nascer sem produtos na lista!
        pedido.setStatus(StatusPedido.NOVO);
        pedidoRepository.salvarPedido(pedido);
    }

    // NOVO MÉTODO: Adicionar produtos depois que a comanda foi aberta
    public void adicionarProdutoAoPedido(Pedido pedido, ItemPedido item) throws Exception {
        if (pedido.getStatus() != StatusPedido.NOVO) {
            throw new Exception("ERRO: Só é possível adicionar itens em pedidos com status NOVO.");
        }
        // Desafio Extra: Aqui você poderia até diminuir a quantidadeEstoque do Produto!
        pedido.adicionarItem(item);
    }

    // ATUALIZADO: Blindando o status!
    public void atualizarStatus(Pedido pedido, StatusPedido novoStatus) throws Exception {
        if (pedido == null) throw new PedidoNaoEncontradoException("Pedido não existe.");

        // Se a cozinha tentar preparar um pedido sem hambúrguer, o sistema bloqueia!
        if (novoStatus == StatusPedido.EM_PREPARO && pedido.getListaItemsPedidos().isEmpty()) {
            throw new Exception("ERRO: Não pode mandar para a cozinha um pedido sem produtos!");
        }

        if (pedido.getStatus() == StatusPedido.FINALIZADO || pedido.getStatus() == StatusPedido.CANCELADO) {
            throw new Exception("ERRO: Este pedido já foi encerrado!");
        }

        pedido.setStatus(novoStatus);
    }

    @Override
    public List<Pedido> listarTodos() { return pedidoRepository.listarTodos(); }

    public List<Pedido> listarPorPrioridade() {
        List<Pedido> pedidos = listarTodos();
        List<Pedido> pedidosOrdenados = new ArrayList<>(pedidos);
        pedidosOrdenados.sort(Comparator.comparing(pedido -> pedido.getPrioridade().getNivel()));
        return pedidosOrdenados;
    }
}