package com.example.abner.revenda_veiculos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abner.revenda_veiculos.R;
import com.example.abner.revenda_veiculos.models.Carro;
import com.example.abner.revenda_veiculos.models.CarroDao;

public class FormularioCarroActivity extends AppCompatActivity {

    private CarroDao mCarroDao;

    private Carro mCarro;

    private TextView mTxtIdVeiculo;
    private EditText mEdtModelo;
    private EditText mEdtAno;
    private Spinner mSpnFabricante;
    private CheckBox mChkGasolina;
    private CheckBox mChkEtanol;
    private EditText mEdtPreco;

    private Button mBtnSalvarVeiculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_carro);

        mCarroDao = new CarroDao(this);

        mTxtIdVeiculo = (TextView) findViewById(R.id.txtIdVeiculo);
        mEdtModelo = (EditText) findViewById(R.id.edtModelo);
        mEdtAno = (EditText) findViewById(R.id.edtAno);
        mSpnFabricante = (Spinner) findViewById(R.id.spnFabricante);
        mChkGasolina = (CheckBox) findViewById(R.id.chkGasolina);
        mChkEtanol = (CheckBox) findViewById(R.id.chkEtanol);
        mEdtPreco = (EditText) findViewById(R.id.edtPreco);
        mBtnSalvarVeiculo = (Button) findViewById(R.id.btnSalvarVeiculo);

        mBtnSalvarVeiculo.setOnClickListener(new View.OnClickListener(){

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

        mSpnFabricante.setAdapter(spinnerAdapter);

        Intent it = getIntent();

        mCarro = (Carro) it.getSerializableExtra("carro");
        if(mCarro != null){
            mTxtIdVeiculo.setText(String.valueOf(mCarro.getId()));
            mEdtModelo.setText(mCarro.getModelo());
            mEdtAno.setText(mCarro.getAno());
            mSpnFabricante.setSelection(mCarro.getFabricante());
            mChkGasolina.setChecked(mCarro.isGasolina());
            mChkEtanol.setChecked(mCarro.isEtanol());
            mEdtPreco.setText(String.valueOf(mCarro.getPreco()));
        }

    }

    private void salvar(){

        String modelo = mEdtModelo.getText().toString();
        int ano = Integer.valueOf(mEdtAno.getText().toString());
        int fabricante = mSpnFabricante.getSelectedItemPosition();
        boolean gasolina = mChkGasolina.isChecked();
        boolean etanol = mChkEtanol.isChecked();
        double preco = Double.valueOf(mEdtPreco.getText().toString());

        if(mCarro == null){
            mCarro = new Carro(
                    modelo,
                    ano,
                    fabricante,
                    gasolina,
                    etanol,
                    preco
            );
        } else {
            mCarro.setModelo(modelo);
            mCarro.setAno(ano);
            mCarro.setFabricante(fabricante);
            mCarro.setGasolina(gasolina);
            mCarro.setEtanol(etanol);
            mCarro.setPreco(preco);
        }

        long id = mCarroDao.salvar(mCarro);

        if(id > 0) {
            Toast.makeText(this, "Carro salvo com sucesso!", Toast.LENGTH_SHORT).show();
        } else {
                Toast.makeText(this, "Erro ao salvar o carro!", Toast.LENGTH_SHORT).show();
        }

        finish();

    }
}
