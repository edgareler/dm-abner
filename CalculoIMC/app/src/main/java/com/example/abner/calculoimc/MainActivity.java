package com.example.abner.calculoimc;
import android.os.RecoverySystem;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Integer mId_resultado;
    private TextView mLbResultado;
    private EditText mPeso;
    private EditText mAltura;
    private float mResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLbResultado = (TextView)findViewById(R.id.lbResultado);
        mPeso = (EditText) findViewById(R.id.edPeso);
        mAltura = (EditText) findViewById(R.id.edAltura);

        if(savedInstanceState != null){
            mResultado = savedInstanceState.getFloat("resultado");
            mId_resultado = savedInstanceState.getInt("id_resultado");
            atualizarResultado();
        }
    }

    public void btnCalcularOnClick(View v){

        int peso = Integer.parseInt(mPeso.getText().toString());
        float altura = Float.parseFloat(mAltura.getText().toString());

        mResultado = peso / (altura * altura);
        if(mResultado < 19){
            mId_resultado = R.string.abaixo_do;
        }
        else if(mResultado > 32){
            mId_resultado = R.string.acima_do;
        }
        else{
            mId_resultado = R.string.ok;
        }
        atualizarResultado();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putFloat("resultado",mResultado);
        outState.putInt("id_resultado",mId_resultado);
    }

    private void atualizarResultado(){
        mLbResultado.setText(getString(mId_resultado) + ". " + getString(R.string.imc) + ": " + mResultado);
    }
}