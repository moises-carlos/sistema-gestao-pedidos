package com.moises.pedidos.model;

import com.moises.pedidos.model.enums.Prioridade;
import com.moises.pedidos.model.enums.StatusPedido;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Pedido {
    private String id;
    private Cliente cliente;
    private List<ItemPedido> listaItemsPedidos = new ArrayList<>();
    private Prioridade prioridade;
    private StatusPedido status;
    private LocalDate dataPedido;

    public Pedido( Cliente cliente, List<ItemPedido> listaItemsPedidos, Prioridade prioridade, StatusPedido status) {
        this.id = null;
        this.cliente = cliente;
        this.listaItemsPedidos = listaItemsPedidos;
        this.prioridade = prioridade;
        this.status = status;
        this.dataPedido = LocalDate.now();
    }

    public Pedido(UUID id, Cliente cliente, List<ItemPedido> listaItemsPedidos, Prioridade prioridade, StatusPedido status, LocalDate dataPedido) {
        this.id = id.toString();
        this.cliente = cliente;
        this.listaItemsPedidos = listaItemsPedidos;
        this.prioridade = prioridade;
        this.status = status;
        this.dataPedido = dataPedido;

    }

    public void adicionarItem(ItemPedido item) {
        this.listaItemsPedidos.add(item);
    }

    public Double calcularValorTotal() {
        Double total = 0.0;
        for (ItemPedido item : listaItemsPedidos) {
            total += item.calcularSubtotal();
        }
        return total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemPedido> getListaItemsPedidos() {
        return listaItemsPedidos;
    }

    public void setListaItemsPedidos(List<ItemPedido> listaItemsPedidos) {
        this.listaItemsPedidos = listaItemsPedidos;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }
}
