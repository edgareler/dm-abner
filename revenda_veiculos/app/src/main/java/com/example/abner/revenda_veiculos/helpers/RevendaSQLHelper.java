package com.example.abner.revenda_veiculos.helpers;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by abner on 14/10/17.
 */

public class RevendaSQLHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "dbRevenda";
    private static final int VERSAO_BANCO = 1;


    public static final String TABELA_CLIENTES = "CLIENTES";
    public static final String TABELA_CARROS = "CARROS";

    public RevendaSQLHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    /*
    TIPOS DE DADOS SQL LITE

        INTEGER
        REAL
        TEXT
        BLOB
     */

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABELA_CLIENTES + " (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "TIPO TEXT NOT NULL, " +
                "DOCUMENTO TEXT, " +
                "NOME TEXT, " +
                "RENDA INTEGER, " +
                "OBSERVACAO TEXT " +
                ") ");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABELA_CARROS + " (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "MODELO TEXT NOT NULL, " +
                "ANO INTEGER NOT NULL, " +
                "FABRICANTE INTEGER NOT NULL, " +
                "GASOLINA INTEGER, " +
                "ETANOL INTEGER, " +
                "PRECO REAL " +
                ") ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // PARA AS PRÓXIMAS VERSÕES
    }
}
