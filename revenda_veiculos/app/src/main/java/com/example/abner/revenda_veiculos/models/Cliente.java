package com.example.abner.revenda_veiculos.models;

import java.io.Serializable;

/**
 * Created by abner on 12/10/17.
 */

public class Cliente implements Serializable{

    private Long id;
    private String tipo;
    private String documento;
    private String nome;
    private int renda;
    private String observacao;

    public Cliente(String tipo, String documento, String nome, int renda, String observacao) {
        this.tipo = tipo;
        this.documento = documento;
        this.nome = nome;
        this.renda = renda;
        this.observacao = observacao;
    }

    public Cliente(Long id, String tipo, String documento, String nome, int renda, String observacao) {
        this.id = id;
        this.tipo = tipo;
        this.documento = documento;
        this.nome = nome;
        this.renda = renda;
        this.observacao = observacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getRenda() {
        return renda;
    }

    public void setRenda(int renda) {
        this.renda = renda;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
