package com.example.abner.revenda_veiculos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.abner.revenda_veiculos.adapters.ClientesAdapter;
import com.example.abner.revenda_veiculos.models.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ControleClientesActivity extends AppCompatActivity {

    List<Cliente> clientes;
    ClientesAdapter adapter;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controle_clientes);

        Button btnAddCliente = (Button) findViewById(R.id.id_btn_addClt);
        btnAddCliente.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ControleClientesActivity.this, FormularioClienteActivity.class);
                startActivity(it);
            }
        });

    }

}
