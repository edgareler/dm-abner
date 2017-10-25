package com.example.abner.revenda_veiculos.models;

import java.io.Serializable;
import java.util.Locale;

/**
 * Created by abner on 21/10/17.
 */

public class Carro implements Serializable {
    public Long id;
    public String modelo = " ";
    public String ano;
    public int fabricante = 0;
    public boolean gasolina = false;
    public boolean etanol = false;
    public String preco;

    public Carro(String modelo, String ano, int fabricante, boolean gasolina, boolean etanol, String preco) {
        this.modelo = modelo;
        this.ano = ano;
        this.fabricante = fabricante;
        this.gasolina = gasolina;
        this.etanol = etanol;
        this.preco = preco;
    }

    public Carro(Long id, String modelo, String ano, int fabricante, boolean gasolina, boolean etanol, String preco) {
        this.id = id;
        this.modelo = modelo;
        this.ano = ano;
        this.fabricante = fabricante;
        this.gasolina = gasolina;
        this.etanol = etanol;
        this.preco = preco;
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

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public int getFabricante() {
        return fabricante;
    }

    public void setFabricante(int fabricante) {
        this.fabricante = fabricante;
    }

    public boolean isGasolina() {
        return gasolina;
    }

    public void setGasolina(boolean gasolina) {
        this.gasolina = gasolina;
    }

    public int getGasolina() {
        return gasolina == true ? 1 : 0;
    }

    public boolean isEtanol() {
        return etanol;
    }

    public int getEtanol() {
        return etanol == true ? 1 : 0;
    }

    public void setEtanol(boolean etanol) {
        this.etanol = etanol;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

}