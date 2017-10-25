package com.example.abner.revenda_veiculos.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.abner.revenda_veiculos.helpers.RevendaSQLHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abner on 24/10/17.
 */

public class CompraDao {

    private RevendaSQLHelper helper;

    public static final String TABELA_COMPRAS = "COMPRAS";
    public static final String COLUNA_ID = "ID";
    public static final String COLUNA_MODELO = "MODELO";
    public static final String COLUNA_FABRICANTE = "FABRICANTE";
    public static final String COLUNA_QUANTIDADE = "QUANTIDADE";

    public CompraDao(Context ctx){
        helper = new RevendaSQLHelper(ctx);
    }

    public long inserir(Compra compra) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUNA_MODELO, compra.getModelo());
        cv.put(COLUNA_FABRICANTE, compra.getFabricante());
        cv.put(COLUNA_QUANTIDADE, compra.getQuantidade());

        long id =  db.insert(TABELA_COMPRAS, null, cv);

        db.close();

        return id;
    }

    public long atualizar(Compra compra) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUNA_MODELO, compra.getModelo());
        cv.put(COLUNA_FABRICANTE, compra.getFabricante());
        cv.put(COLUNA_QUANTIDADE, compra.getQuantidade());

        long linhasAfetadas =  db.update(
                TABELA_COMPRAS,
                cv,
                COLUNA_ID + "= ?",
                new String[]{String.valueOf(compra.getId())}
        );

        db.close();

        return linhasAfetadas;
    }

    public long salvar(Compra compra) {
        if(compra.getId() != null) {
            return atualizar(compra);
        }

        return inserir(compra);

    }

    public int excluir(Compra compra) {
        SQLiteDatabase db = helper.getWritableDatabase();

        int linhasExcluidas = db.delete(
                TABELA_COMPRAS,
                COLUNA_ID + " = ?", //CONDIÇÃO DO WHERE
                new String[] {String.valueOf(compra.getId())}
                // |-> VALORES DOS PARÂMETROS PASSADOS NO WHERE (AS INTERROGAÇÕES)
        );

        db.close();

        return linhasExcluidas;
    }

    public List<Compra> all() {
        return buscarCompra(null);
    }

    public List<Compra> buscarCompra(String filtro) {
        SQLiteDatabase db = helper.getReadableDatabase();

        String  sql = "SELECT * FROM " + TABELA_COMPRAS;

        String[] argumentos = null;

        if(filtro != null) {
            sql += " WHERE " + COLUNA_MODELO + "LIKE ?";
            argumentos = new String[]{ filtro };
        }

        sql += " ORDER BY " + COLUNA_MODELO;

        Cursor cursor = db.rawQuery(sql, argumentos);

        List<Compra> compras = new ArrayList<>();

        while(cursor.moveToNext()) {
            Long id = cursor.getLong(cursor.getColumnIndex(COLUNA_ID));
            String modelo = cursor.getString(cursor.getColumnIndex(COLUNA_MODELO));
            int fabricante = cursor.getInt(cursor.getColumnIndex(COLUNA_FABRICANTE));
            String quantidade = cursor.getString(cursor.getColumnIndex(COLUNA_QUANTIDADE));

            Compra compra = new Compra(id, modelo, fabricante, quantidade);
            compras.add(compra);
        }

        cursor.close();
        db.close();

        return compras;
    }

}
