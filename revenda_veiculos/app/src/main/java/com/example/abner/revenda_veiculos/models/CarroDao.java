package com.example.abner.revenda_veiculos.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.abner.revenda_veiculos.helpers.RevendaSQLHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abner on 21/10/17.
 */

public class CarroDao {
    private RevendaSQLHelper helper;

    public static final String TABELA_CARROS = "CARROS";
    public static final String COLUNA_ID = "ID";
    public static final String COLUNA_MODELO = "MODELO";
    public static final String COLUNA_ANO = "ANO";
    public static final String COLUNA_FABRICANTE = "FABRICANTE";
    public static final String COLUNA_GASOLINA = "GASOLINA";
    public static final String COLUNA_ETANOL = "ETANOL";
    public static final String COLUNA_PRECO = "PRECO";

    public CarroDao(Context ctx) {
        helper = new RevendaSQLHelper(ctx); }

    public long inserir(Carro carro) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUNA_MODELO, carro.getModelo());
        cv.put(COLUNA_ANO, carro.getAno());
        cv.put(COLUNA_FABRICANTE, carro.getFabricante());
        cv.put(COLUNA_GASOLINA, carro.getGasolina());
        cv.put(COLUNA_ETANOL, carro.getEtanol());
        cv.put(COLUNA_PRECO, carro.getPreco());

        long id = db.insert(TABELA_CARROS, null, cv);

        db.close();

        return id;
    }

    public long atualizar(Carro carro) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUNA_MODELO, carro.getModelo());
        cv.put(COLUNA_ANO, carro.getAno());
        cv.put(COLUNA_FABRICANTE, carro.getFabricante());
        cv.put(COLUNA_GASOLINA, carro.getGasolina());
        cv.put(COLUNA_ETANOL, carro.getEtanol());
        cv.put(COLUNA_PRECO, carro.getPreco());

        long linhasAfetadas =  db.update(
                TABELA_CARROS,
                cv,
                COLUNA_ID + "= ?",
                new String[]{String.valueOf(carro.getId())}
        );

        db.close();

        return linhasAfetadas;
    }

    public long salvar(Carro carro) {
        if(carro.getId() != null) {
            return atualizar(carro);
        }

        return inserir(carro);

    }

    public int excluir(Carro carro) {
        SQLiteDatabase db = helper.getWritableDatabase();

        int linhasExcluidas = db.delete(
                TABELA_CARROS,
                COLUNA_ID + " = ?", //CONDIÇÃO DO WHERE
                new String[] {String.valueOf(carro.getId())}
                // |-> VALORES DOS PARÂMETROS PASSADOS NO WHERE (AS INTERROGAÇÕES)
        );

        db.close();

        return linhasExcluidas;
    }

    public List<Carro> all() {
        return buscarCarro(null);
    }

    public List<Carro> buscarCarro(String filtro) {
        SQLiteDatabase db = helper.getReadableDatabase();

        String  sql = "SELECT * FROM " + TABELA_CARROS;

        String[] argumentos = null;

        if(filtro != null) {
            sql += " WHERE " + COLUNA_MODELO + "LIKE ?";
            argumentos = new String[]{ filtro };
        }

        sql += " ORDER BY " + COLUNA_MODELO;

        Cursor cursor = db.rawQuery(sql, argumentos);

        List<Carro> carros = new ArrayList<>();

        while(cursor.moveToNext()) {
            Long id = cursor.getLong(cursor.getColumnIndex(COLUNA_ID));
            String modelo = cursor.getString(cursor.getColumnIndex(COLUNA_MODELO));
            int ano = cursor.getInt(cursor.getColumnIndex(COLUNA_ANO));
            int fabricante = cursor.getInt(cursor.getColumnIndex(COLUNA_FABRICANTE));
            boolean gasolina = cursor.getInt(cursor.getColumnIndex(COLUNA_GASOLINA)) == 1;
            boolean etanol = cursor.getInt(cursor.getColumnIndex(COLUNA_ETANOL)) == 1;
            double preco = cursor.getDouble(cursor.getColumnIndex(COLUNA_PRECO));

            Carro carro = new Carro(id, modelo, ano, fabricante, gasolina, etanol, preco);
            carros.add(carro);
        }

        cursor.close();
        db.close();

        return carros;
    }
}
