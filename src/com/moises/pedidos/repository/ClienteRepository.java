package com.moises.pedidos.repository;
import com.moises.pedidos.model.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClienteRepository {
    private List<Cliente> listaClientes = new ArrayList<>();

    public ClienteRepository() {

    }

    public void salvarCliente(Cliente cliente){
        if (cliente.getId() == null){
            cliente.setId(UUID.randomUUID());
        }
        listaClientes.add(cliente);
    }

    public Cliente buscaPorId(UUID id){
        for (Cliente cliente : listaClientes){
            if (cliente.getId().equals(id)){
                return cliente;
            }
        }
        return null;
    }
    public List<Cliente> listarTodos() {
        return listaClientes;
    }
}
