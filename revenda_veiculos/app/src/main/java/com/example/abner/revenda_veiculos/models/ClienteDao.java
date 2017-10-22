package com.example.abner.revenda_veiculos.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.abner.revenda_veiculos.helpers.RevendaSQLHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abner on 15/10/17.
 */

public class ClienteDao {
    private RevendaSQLHelper helper;

    public static final String TABELA_CLIENTES = "CLIENTES";
    public static final String COLUNA_ID = "ID";
    public static final String COLUNA_TIPO = "TIPO";
    public static final String COLUNA_DOCUMENTO = "DOCUMENTO";
    public static final String COLUNA_NOME = "NOME";
    public static final String COLUNA_RENDA = "RENDA";
    public static final String COLUNA_OBSERVACAO = "OBSERVACAO";

    public ClienteDao(Context ctx){
        helper = new RevendaSQLHelper(ctx);
    }

    public long inserir(Cliente cliente) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUNA_TIPO, cliente.getTipo());
        cv.put(COLUNA_DOCUMENTO, cliente.getDocumento());
        cv.put(COLUNA_NOME, cliente.getNome());
        cv.put(COLUNA_RENDA, cliente.getRenda());
        cv.put(COLUNA_OBSERVACAO, cliente.getObservacao());

        long id =  db.insert(TABELA_CLIENTES, null, cv);

        db.close();

        return id;
    }

    public long atualizar(Cliente cliente) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUNA_TIPO, cliente.getTipo());
        cv.put(COLUNA_DOCUMENTO, cliente.getDocumento());
        cv.put(COLUNA_NOME, cliente.getNome());
        cv.put(COLUNA_RENDA, cliente.getRenda());
        cv.put(COLUNA_OBSERVACAO, cliente.getObservacao());

        long linhasAfetadas =  db.update(
                TABELA_CLIENTES,
                cv,
                    COLUNA_ID + "= ?",
                    new String[]{String.valueOf(cliente.getId())}
                );

        db.close();

        return linhasAfetadas;
    }

    public long salvar(Cliente cliente) {
        if(cliente.getId() != null) {
            return atualizar(cliente);
        }

        return inserir(cliente);

    }

    public int excluir(Cliente cliente) {
        SQLiteDatabase db = helper.getWritableDatabase();

        int linhasExcluidas = db.delete(
                TABELA_CLIENTES,
                COLUNA_ID + " = ?", //CONDIÇÃO DO WHERE
                new String[] {String.valueOf(cliente.getId())}
                // |-> VALORES DOS PARÂMETROS PASSADOS NO WHERE (AS INTERROGAÇÕES)
        );

        db.close();

        return linhasExcluidas;
    }

    public List<Cliente> all() {
            return buscarCliente(null);
    }

    public List<Cliente> buscarCliente(String filtro) {
        SQLiteDatabase db = helper.getReadableDatabase();

        String  sql = "SELECT * FROM " + TABELA_CLIENTES;

        String[] argumentos = null;

        if(filtro != null) {
            sql += " WHERE " + COLUNA_NOME + "LIKE ?";
            argumentos = new String[]{ filtro };
        }

        sql += " ORDER BY " + COLUNA_NOME;

        Cursor cursor = db.rawQuery(sql, argumentos);

        List<Cliente> clientes = new ArrayList<>();

        while(cursor.moveToNext()) {
            Long id = cursor.getLong(cursor.getColumnIndex(COLUNA_ID));
            String tipo = cursor.getString(cursor.getColumnIndex(COLUNA_TIPO));
            String documento = cursor.getString(cursor.getColumnIndex(COLUNA_DOCUMENTO));
            String nome = cursor.getString(cursor.getColumnIndex(COLUNA_NOME));
            int renda = cursor.getInt(cursor.getColumnIndex(COLUNA_RENDA));
            String observacao = cursor.getString(cursor.getColumnIndex(COLUNA_OBSERVACAO));

            Cliente cliente = new Cliente(id, tipo, documento, nome, renda, observacao);
            clientes.add(cliente);
        }

        cursor.close();
        db.close();

        return clientes;
    }

}
