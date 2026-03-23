package com.moises.pedidos.service;

import com.moises.pedidos.core.CrudService;
import com.moises.pedidos.model.Cliente;
import com.moises.pedidos.model.Produto;
import com.moises.pedidos.repository.ClienteRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ClienteService implements CrudService<Cliente> {
    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    @Override
    public void cadastrar(Cliente cliente) throws Exception {
        validarDados(cliente);
        clienteRepository.salvarCliente(cliente);
    }

    private void validarDados(Cliente cliente) throws Exception{
            if (cliente.getNome() == null || cliente.getNome().isBlank()){
                throw new Exception("ERRO: Nome é obrigatorio");
            }

            if (cliente.getTelefone() == null){
                throw new Exception("ERRO: Telefone é obrigatorio");
            }

            if(cliente.getCpf() != null && !cliente.getCpf().isBlank()){
                for ( Cliente clienteExiste : clienteRepository.listarTodos()){
                    if (cliente.getCpf().equals(clienteExiste.getCpf())){
                        throw new Exception("ERRO: Este CPF já está cadastrado no sistema!");
                    }

                }
            }
    }

    @Override
    public List<Cliente> listarTodos() {
        return clienteRepository.listarTodos();
    }

    public List<Cliente> listarPorNome() {

        List<Cliente> clientes = listarTodos();
        List<Cliente> clientesOrdenados = new ArrayList<>(clientes);

        clientesOrdenados.sort(Comparator.comparing(Cliente::getNome));

        return clientesOrdenados;
    }
}
