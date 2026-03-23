package com.moises.pedidos.model;

import java.util.UUID;

public class Cliente {
    private UUID id;
    private String nome;
    private Long telefone;
    private String cpf;
    private String estado;
    private String bairro;
    private String rua;



    public Cliente(String nome, Long telefone) {
        this.id = null;
        this.nome = nome;
        this.telefone = telefone;
    }


    public Cliente(UUID id, String nome, Long telefone) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getTelefone() {
        return telefone;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    @Override
    public String toString() {
        return "Cliente: " + nome + " | CPF: " + cpf;
    }
}
