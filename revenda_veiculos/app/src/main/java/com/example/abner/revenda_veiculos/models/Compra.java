package com.example.abner.revenda_veiculos.models;

import java.io.Serializable;

/**
 * Created by abner on 24/10/17.
 */

public class Compra implements Serializable {

    public Long id;
    public String modelo;
    public int fabricante;
    public String quantidade;

    public Compra(String modelo, int fabricante, String quantidade) {
        this.modelo = modelo;
        this.fabricante = fabricante;
        this.quantidade = quantidade;
    }

    public Compra(Long id, String modelo, int fabricante, String quantidade) {
        this.id = id;
        this.modelo = modelo;
        this.fabricante = fabricante;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getFabricante() {
        return fabricante;
    }

    public void setFabricante(int fabricante) {
        this.fabricante = fabricante;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }
}
