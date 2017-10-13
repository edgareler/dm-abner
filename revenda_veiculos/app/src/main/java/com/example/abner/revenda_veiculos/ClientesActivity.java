package com.example.abner.revenda_veiculos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.abner.revenda_veiculos.adapters.ClientesAdapter;
import com.example.abner.revenda_veiculos.models.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClientesActivity extends AppCompatActivity {

    List<Cliente> clientes;
    ClientesAdapter adapter;
    ListView lista;

    private View.OnClickListener mTratadorCliques = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent itAddClt = new Intent(view.getContext(), CadastroClienteActivity.class);
            startActivity(itAddClt);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        lista = (ListView) findViewById(R.id.idListClientes);

        clientes = new ArrayList<>();
        clientes.add(new Cliente("Abner Bulhões", 0));
        clientes.add(new Cliente("Marcos Uliana", 0));
        clientes.add(new Cliente("Santa Terezinha", 1));
        clientes.add(new Cliente("José Tiago", 0));
        clientes.add(new Cliente("Mercadão", 1));
        adapter = new ClientesAdapter(this, clientes);

        lista.setAdapter(adapter);

        Button btnAddCliente = (Button) findViewById(R.id.id_btn_addClt);
        btnAddCliente.setOnClickListener(mTratadorCliques);

    }
}
