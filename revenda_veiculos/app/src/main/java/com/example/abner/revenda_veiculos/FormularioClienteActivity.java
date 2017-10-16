package com.example.abner.revenda_veiculos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abner.revenda_veiculos.models.Cliente;
import com.example.abner.revenda_veiculos.models.ClienteDao;

public class FormularioClienteActivity extends AppCompatActivity {

    private ClienteDao mClienteDao;

    private TextView mTxtId;
    private EditText mEdtDoc;
    private EditText mEdtNome;
    private Spinner mSpnRenda;
    private EditText mEdtObs;
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

        mBtnSalvar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                salvar();
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

    }
    private void salvar(){

        String documento = mEdtDoc.getText().toString();
        String nome = mEdtNome.getText().toString();
        int renda = mSpnRenda.getSelectedItemPosition();
        String observacao = mEdtObs.getText().toString();

        Cliente cliente = new Cliente(
                documento,
                nome,
                renda,
                observacao
                );

        long id = mClienteDao.inserir(cliente);

        if(id > 0) {
            Toast.makeText(this, "Cliente salvo com sucesso!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Erro ao salvar o cliente!", Toast.LENGTH_SHORT).show();
        }

    }
}
