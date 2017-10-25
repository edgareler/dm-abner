package com.example.abner.revenda_veiculos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.abner.revenda_veiculos.adapters.ComprasAdapter;
import com.example.abner.revenda_veiculos.models.Compra;
import com.example.abner.revenda_veiculos.models.CompraDao;

import java.util.List;

public class ControleComprasActivity extends AppCompatActivity {

    List<Compra> compras;
    ComprasAdapter adapterCompras;
    ListView listaCompras;
    private CompraDao mCompraDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controle_compras);

        mCompraDao = new CompraDao(this);

        Button btnAddCompra = (Button) findViewById(R.id.id_btn_addCompras);

        btnAddCompra.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ControleComprasActivity.this, FormularioCompraActivity.class);
                startActivity(it);
            }
        });

        listaCompras = (ListView) findViewById(R.id.idListCompras);

        listaCompras.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Compra compra = (Compra) adapterView.getItemAtPosition(i);

                Intent novo = new Intent(ControleComprasActivity.this, FormularioCompraActivity.class);
                novo.putExtra("compra", compra);
                startActivity(novo);
            }
        });

        listaCompras.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Compra compra = (Compra) adapterView.getItemAtPosition(i);

                mCompraDao.excluir(compra);

                atualizarListaCompra();

                Toast.makeText(ControleComprasActivity.this, "Compra excluida com sucesso!", Toast.LENGTH_SHORT).show();

                return false;
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        compras = mCompraDao.all();

        adapterCompras = new ComprasAdapter(this, compras);

        listaCompras.setAdapter(adapterCompras);

    }

    private void atualizarListaCompra() {

        compras = mCompraDao.all();

        adapterCompras.notifyDataSetChanged();

    }

}
