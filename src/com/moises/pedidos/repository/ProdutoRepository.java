package com.moises.pedidos.repository;

import com.moises.pedidos.model.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProdutoRepository {
    private List<Produto> listaProdutos = new ArrayList<>();

    public ProdutoRepository() {

    }

    public void salvarProduto(Produto produto){
        if (produto.getId() == null){
            produto.setId(UUID.randomUUID());
        }
        listaProdutos.add(produto);
    }

    public Produto buscaPorId(UUID id){
        for (Produto produto : listaProdutos){
            if (produto.getId().equals(id)){
                return produto;
            }
        }
        return null;
    }
    public List<Produto> listarTodos() {
        return listaProdutos;
    }
}
