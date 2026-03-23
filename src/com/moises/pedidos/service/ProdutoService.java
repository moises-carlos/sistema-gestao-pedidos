package com.moises.pedidos.service;

import com.moises.pedidos.core.CrudService;
import com.moises.pedidos.model.Produto;
import com.moises.pedidos.repository.ProdutoRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProdutoService implements CrudService<Produto> {
    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    @Override
    public void cadastrar(Produto produto) throws Exception {
        validarDadosPro(produto);
        produtoRepository.salvarProduto(produto);
    }

    private void validarDadosPro(Produto produto) throws Exception{
        if (produto.getNome()== null || produto.getNome().isBlank()){
            throw new Exception("ERRO: Nome do produto é obrigatorio");
        }
        if (produto.getPreco() == null || produto.getPreco() <= 0){
            throw new Exception("ERRO: O preço é obrigatório e deve ser maior que zero!");
        }
    }

    @Override
    public List<Produto> listarTodos() {
        return produtoRepository.listarTodos();
    }

    public List<Produto> listarPorNome() {

        List<Produto> produtos = listarTodos();
        List<Produto> produtosOrdenados = new ArrayList<>(produtos);

        produtosOrdenados.sort(Comparator.comparing(Produto::getNome));

        return produtosOrdenados;
    }


}
