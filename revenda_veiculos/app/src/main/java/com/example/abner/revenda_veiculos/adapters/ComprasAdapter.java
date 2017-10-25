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

import com.example.abner.revenda_veiculos.ControleComprasActivity;
import com.example.abner.revenda_veiculos.R;
import com.example.abner.revenda_veiculos.models.Carro;
import com.example.abner.revenda_veiculos.models.Compra;

import java.util.List;

/**
 * Created by abner on 24/10/17.
 */

public class ComprasAdapter extends BaseAdapter{

    Context ctx;
    List<Compra> compras;

    public ComprasAdapter(Context ctx, List<Compra> compras) {
        this.ctx = ctx;
        this.compras = compras;
    }

    @Override
    public int getCount() {
        return compras.size();
    }

    @Override
    public Object getItem(int i) {
        return compras.get(i);
    }

    @Override
    public long getItemId(int i) {
        return compras.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        // Pegar o objeto do carro daquele item da lista
        Compra compra = compras.get(i);
        // Inflar a view que criamos para cada item
        View linha = LayoutInflater.from(ctx).inflate(R.layout.item_compras, null);

        // Customizar os componentes da view para este cliente
        ImageView imgLogo2 = (ImageView) linha.findViewById(R.id.imgLogo2);
        TextView txtIdCompra = (TextView) linha.findViewById(R.id.txtIdCompraMain);
        TextView txtCompraModelo= (TextView) linha.findViewById(R.id.txtCompraModelo);
        TextView txtCompraQuantidade= (TextView) linha.findViewById(R.id.txtCompraQtd);

        // 0=vw 1=gm 2=fiat 3=ford
        Resources reso = ctx.getResources();
        TypedArray logosCompra = reso.obtainTypedArray(R.array.logos);
        imgLogo2.setImageDrawable(logosCompra.getDrawable(compra.getFabricante()));

        txtIdCompra.setText(compra.getId().toString());
        txtCompraModelo.setText(compra.getModelo());
        txtCompraQuantidade.setText(compra.getQuantidade());
        // Retornar view
        return linha;
    }
}
