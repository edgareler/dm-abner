package com.example.abner.revenda_veiculos.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.abner.revenda_veiculos.R;
import com.example.abner.revenda_veiculos.models.Cliente;

import java.util.List;

/**
 * Created by abner on 12/10/17.
 */

public class ClientesAdapter extends BaseAdapter {


    Context ctx;
    List<Cliente> clientes;

    public ClientesAdapter(Context ctx, List<Cliente> clientes) {
        this.ctx = ctx;
        this.clientes = clientes;
    }

    @Override
    public int getCount() {
        return clientes.size();
    }

    @Override
    public Object getItem(int i) {
        return clientes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
