package com.example.abner.revenda_veiculos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private OnClickListener mTratadorCliques = new OnClickListener() {

        @Override
        public void onClick(View view) {

            switch(view.getId()){
                case R.id.id_btn_veiculo:
                    Intent itVei = new Intent(MainActivity.this, ControleCarrosActivity.class);
                    startActivity(itVei);
                    break;
                case R.id.id_btn_clientes:
                    Intent itClt = new Intent(MainActivity.this, ControleClientesActivity.class);
                    startActivity(itClt);
                    break;
                case R.id.id_btn_compras:
                    Intent itComp = new Intent(MainActivity.this, ControleComprasActivity.class);
                    startActivity(itComp);
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnControlVei = (Button) findViewById(R.id.id_btn_veiculo);
        Button btnControlClt = (Button) findViewById(R.id.id_btn_clientes);
        Button btnControlComp = (Button) findViewById(R.id.id_btn_compras);
        btnControlVei.setOnClickListener(mTratadorCliques);
        btnControlClt.setOnClickListener(mTratadorCliques);
        btnControlComp.setOnClickListener(mTratadorCliques);
    }
}
