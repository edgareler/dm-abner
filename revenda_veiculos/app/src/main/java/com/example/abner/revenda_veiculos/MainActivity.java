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
                case R.id.id_btn_clientes:
                    Intent itClt = new Intent(MainActivity.this, ControleClientesActivity.class);
                    startActivity(itClt);
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnControlClt = (Button) findViewById(R.id.id_btn_clientes);
        btnControlClt.setOnClickListener(mTratadorCliques);
    }
}
