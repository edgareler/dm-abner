package com.example.abner.revenda_veiculos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.abner.revenda_veiculos.adapters.CarrosAdapter;
import com.example.abner.revenda_veiculos.models.Carro;
import com.example.abner.revenda_veiculos.models.CarroDao;

import java.util.List;

public class ControleCarrosActivity extends AppCompatActivity {

    List<Carro> carros;
    CarrosAdapter adapter;
    ListView lista;
    private CarroDao mCarroDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controle_carros);

        mCarroDao = new CarroDao(this);

        Button btnAddCarro = (Button) findViewById(R.id.btnAddCarro);

        btnAddCarro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ControleCarrosActivity.this, FormularioCarroActivity.class);
                startActivity(it);
            }
        });

        lista = (ListView) findViewById(R.id.listaCarros);


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Carro carro = (Carro) adapterView.getItemAtPosition(i);

                Intent novo= new Intent(ControleCarrosActivity.this, FormularioCarroActivity.class);
                novo.putExtra("carro", carro);
                startActivity(novo);
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Carro carro = (Carro) adapterView.getItemAtPosition(i);

                mCarroDao.excluir(carro);

                atualizarListaCarro();

                Toast.makeText(ControleCarrosActivity.this, "Carro excluido com sucesso!", Toast.LENGTH_SHORT).show();

                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        carros = mCarroDao.all();

        adapter = new CarrosAdapter(this, carros);

        lista.setAdapter(adapter);

    }

    private void atualizarListaCarro() {

        carros = mCarroDao.all();

        adapter.notifyDataSetChanged();

    }

}
