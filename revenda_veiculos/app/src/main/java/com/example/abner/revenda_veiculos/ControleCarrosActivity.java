package com.example.abner.revenda_veiculos.models;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.widget.ListView;

import com.example.abner.revenda_veiculos.R;
import com.example.abner.revenda_veiculos.adapters.CarrosAdapter;

import java.util.List;

public class ControleCarrosActivity extends AppCompatActivity {

    List<Carro> carros;
    CarrosAdapter adapter;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controle_carros);

        lista = (ListView) findViewById(R.id.listaCarros);


    }
}
