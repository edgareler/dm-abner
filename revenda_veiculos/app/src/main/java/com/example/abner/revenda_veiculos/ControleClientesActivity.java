package com.example.abner.revenda_veiculos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.abner.revenda_veiculos.adapters.ClientesAdapter;
import com.example.abner.revenda_veiculos.models.Cliente;
import com.example.abner.revenda_veiculos.models.ClienteDao;

import java.util.ArrayList;
import java.util.List;

public class ControleClientesActivity extends AppCompatActivity {

    List<Cliente> clientes;
    ClientesAdapter adapter;
    ListView lista;
    private ClienteDao mClienteDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controle_clientes);

        mClienteDao = new ClienteDao(this);

        Button btnAddCliente = (Button) findViewById(R.id.id_btn_addClt);

        btnAddCliente.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ControleClientesActivity.this, FormularioClienteActivity.class);
                startActivity(it);
            }
        });

        lista = (ListView) findViewById(R.id.idListClientes);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cliente cliente = (Cliente) adapterView.getItemAtPosition(i);

                Intent it = new Intent(ControleClientesActivity.this, FormularioClienteActivity.class);
                it.putExtra("cliente", cliente);
                startActivity(it);
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cliente cliente = (Cliente) adapterView.getItemAtPosition(i);

                mClienteDao.excluir(cliente);

                atualizarLista();

                Toast.makeText(ControleClientesActivity.this, "Carro excluido com sucesso!", Toast.LENGTH_SHORT).show();

                return false;
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        clientes = mClienteDao.all();

        adapter = new ClientesAdapter(this, clientes);

        lista.setAdapter(adapter);

    }

    private void atualizarLista() {

        clientes = mClienteDao.all();

        adapter.notifyDataSetChanged();

    }

}
