package com.moises.pedidos.exception;

public class ProdutoNaoEncotradoException extends RuntimeException {
    public ProdutoNaoEncotradoException(String message) {
        super(message);
    }
}
