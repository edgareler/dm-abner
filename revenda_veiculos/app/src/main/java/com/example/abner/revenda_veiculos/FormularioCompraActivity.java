package com.example.abner.revenda_veiculos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abner.revenda_veiculos.models.Compra;
import com.example.abner.revenda_veiculos.models.CompraDao;

public class FormularioCompraActivity extends AppCompatActivity {

    private CompraDao mCompraDao;

    private Compra mCompra;

    private TextView mTxtIdCompra;
    private EditText mEdtModelo;
    private Spinner mSpnFornecedor;
    private EditText mEdtQtd;

    private Button mBtnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_compra);

        mCompraDao = new CompraDao(this);

        mTxtIdCompra = (TextView) findViewById(R.id.txtIdCompra);
        mEdtModelo = (EditText) findViewById(R.id.edtModeloCompra);
        mSpnFornecedor = (Spinner) findViewById(R.id.spnFabricanteCompras);
        mEdtQtd = (EditText) findViewById(R.id.edtQtd);
        mBtnSalvar = (Button) findViewById(R.id.btnSaveCompra);

        mBtnSalvar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                salvar();
            }
        });

        String[] fabricantes = new String[]{
                "VW", "GM ", "Fiat", "Ford"
        };

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, fabricantes
        );

        spinnerAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        mSpnFornecedor.setAdapter(spinnerAdapter);

        Intent it = getIntent();

        mCompra = (Compra) it.getSerializableExtra("compra");
        if(mCompra != null){
            mTxtIdCompra.setText(String.valueOf(mCompra.getId()));
            mEdtModelo.setText(mCompra.getModelo());
            mSpnFornecedor.setSelection(mCompra.getFabricante());
            mEdtQtd.setText(mCompra.getQuantidade());
        }

    }


    private void salvar(){

        String modelo = mEdtModelo.getText().toString();
        int fabricante = mSpnFornecedor.getSelectedItemPosition();
        String quantidade = mEdtQtd.getText().toString();

        if(mCompra == null){
            mCompra = new Compra(
                    modelo,
                    fabricante,
                    quantidade
            );
        } else {
            mCompra.setModelo(modelo);
            mCompra.setFabricante(fabricante);
            mCompra.setQuantidade(quantidade);
        }

        long id = mCompraDao.salvar(mCompra);

        if(id > 0) {
            Toast.makeText(this, "Compra salva com sucesso!", Toast.LENGTH_SHORT).show();
        }

        finish();

    }
}
