package com.example.abner.revenda_veiculos.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abner.revenda_veiculos.R;
import com.example.abner.revenda_veiculos.models.Carro;

import java.util.List;

/**
 * Created by abner on 21/10/17.
 */

public class CarrosAdapter extends BaseAdapter {

    Context ctx;
    List<Carro> carros;

    public CarrosAdapter(Context ctx, List<Carro> carros) {
        this.ctx = ctx;
        this.carros = carros;
    }

    @Override
    public int getCount() {
        return carros.size();
    }

    @Override
    public Object getItem(int i) {
        return carros.get(i);
    }

    @Override
    public long getItemId(int i) {
        return carros.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // Pegar o objeto do carro daquele item da lista
        Carro carro = carros.get(i);
        // Inflar a view que criamos para cada item
        View linha = LayoutInflater.from(ctx).inflate(R.layout.item_carro, null);

        // Customizar os componentes da view para este cliente
        ImageView imgLogo = (ImageView) linha.findViewById(R.id.imgLogo);
        TextView txtModelo= (TextView) linha.findViewById(R.id.txtModelo);
        TextView txtAno= (TextView) linha.findViewById(R.id.txtAno);
        TextView txtCombustivel= (TextView) linha.findViewById(R.id.txtCombustivel);

        // 0=vw 1=gm 2=fiat 3=ford
        Resources res = ctx.getResources();
        TypedArray logos = res.obtainTypedArray(R.array.logos);
        imgLogo.setImageDrawable(logos.getDrawable(carro.getFabricante()));

        txtModelo.setText(carro.getModelo());
        txtAno.setText(String.valueOf(carro.getAno()));
        String combustivel = (carro.isGasolina() ? "G" : "") +
                (carro.isEtanol() ? "E" : "");
        txtCombustivel.setText(combustivel);
        // Retornar view
        return linha;
    }
}
