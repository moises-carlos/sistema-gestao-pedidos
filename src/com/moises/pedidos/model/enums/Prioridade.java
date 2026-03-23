package com.moises.pedidos.model.enums;

public enum Prioridade {
    URGENTE(1),
    NORMAL(2),
    BAIXA(3);

    private final int nivel;

    Prioridade(int nivel) {
        this.nivel = nivel;
    }

    public int getNivel() {
        return nivel;
    }
}
