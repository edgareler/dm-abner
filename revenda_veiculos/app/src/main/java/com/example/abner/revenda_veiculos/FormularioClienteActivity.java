package com.example.abner.revenda_veiculos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abner.revenda_veiculos.models.Cliente;
import com.example.abner.revenda_veiculos.models.ClienteDao;

public class FormularioClienteActivity extends AppCompatActivity {

    private ClienteDao mClienteDao;

    private Cliente mCliente;

    private TextView mTxtId;
    private EditText mEdtDoc;
    private EditText mEdtNome;
    private Spinner mSpnRenda;
    private EditText mEdtObs;
    private String mTipo = null;

    private RadioGroup mRgTipo;

    private Button mBtnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_cliente);

        mClienteDao = new ClienteDao(this);

        mTxtId = (TextView) findViewById(R.id.txtId);
        mEdtDoc = (EditText) findViewById(R.id.edtDoc);
        mEdtNome = (EditText) findViewById(R.id.edtNome);
        mSpnRenda = (Spinner) findViewById(R.id.spnRenda);
        mEdtObs = (EditText) findViewById(R.id.edtObj);
        mBtnSalvar = (Button) findViewById(R.id.btnSalvar);

        mRgTipo = (RadioGroup) findViewById(R.id.rgTipo);

        mBtnSalvar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                salvar();
            }
        });

        // When radio group "Tipo" checked change.
        mRgTipo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                tipoEscolhido(group, checkedId);
            }
        });

        String[] renda = new String[]{
          "1000", "2000", "3000", "4000", "5000"
        };

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, renda
        );

        spinnerAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        mSpnRenda.setAdapter(spinnerAdapter);

        Intent it = getIntent();

        mCliente = (Cliente) it.getSerializableExtra("cliente");
        if(mCliente != null){
            mTxtId.setText(String.valueOf(mCliente.getId()));
            mEdtDoc.setText(mCliente.getDocumento());
            mEdtNome.setText(mCliente.getNome());
            mSpnRenda.setSelection(mCliente.getRenda());
            mEdtObs.setText(mCliente.getObservacao());
        }

    }

    // When radio group "tipo" checked change.
    private void tipoEscolhido(RadioGroup group, int checkedId) {
        int checkedRadioId = group.getCheckedRadioButtonId();

        if(checkedRadioId== R.id.rbPf) {
            mTipo = "Pessoa Física";
        } else if(checkedRadioId== R.id.rbPj ) {
            mTipo = "Pessoa Jurídica";
        }
    }

    private void salvar(){

        String tipo = mTipo;
        String documento = mEdtDoc.getText().toString();
        String nome = mEdtNome.getText().toString();
        int renda = mSpnRenda.getSelectedItemPosition();
        String observacao = mEdtObs.getText().toString();

        if(mCliente == null){
            mCliente = new Cliente(
                    tipo,
                    documento,
                    nome,
                    renda,
                    observacao
            );
        } else {
            mCliente.setDocumento(documento);
            mCliente.setNome(nome);
            mCliente.setRenda(renda);
            mCliente.setObservacao(observacao);
        }

        long id = mClienteDao.salvar(mCliente);

        if(id > 0) {
            Toast.makeText(this, "Cliente salvo com sucesso!", Toast.LENGTH_SHORT).show();
        } else {

            if(tipo== null){
                Toast.makeText(this, "ERRO: SELECIONE UM TIPO DE PESSOA", Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(this, "Erro ao salvar o cliente!", Toast.LENGTH_SHORT).show();
            }
        }

        finish();

    }
}
