package com.moises.pedidos.repository;

import com.moises.pedidos.model.Pedido;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PedidoRepository {
    private List<Pedido> listaPedidos = new ArrayList<>();

    public PedidoRepository() {
    }


    public void salvarPedido(Pedido pedido){
        if (pedido.getId() == null){
            pedido.setId(UUID.randomUUID().toString());
        }
        listaPedidos.add(pedido);
    }

    public Pedido buscarPedido(String id){
            for (Pedido pedido : listaPedidos){
                if (pedido.getId().equals(id)){
                    return pedido;
                }
            }
        return null;
    }


    public List<Pedido> listarTodos() {
        return listaPedidos;
    }
}
