package com.example.abner.revenda_veiculos.models;

/**
 * Created by abner on 12/10/17.
 */

public class Cliente {
    public int id;
    public String nome;
    public int tipo;
    public String doc;
    public int renda;
    public String obs;

    public Cliente(String nome, int tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public Cliente(String nome, int tipo, String doc, int renda) {
        this.nome = nome;
        this.tipo = tipo;
        this.doc = doc;
        this.renda = renda;
    }
}
