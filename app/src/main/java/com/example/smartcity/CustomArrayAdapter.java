package com.example.smartcity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;


public class CustomArrayAdapter extends ArrayAdapter<Notas> {

    public CustomArrayAdapter(@NonNull Context context, ArrayList<Notas> users) {
        super(context, 0, users);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        Notas n = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_linha, parent, false);
        }
        ((TextView) convertView.findViewById(R.id.titulo)).setText(n.getTitulo());
        ((TextView) convertView.findViewById(R.id.data)).setText(n.getData());
        ((TextView) convertView.findViewById(R.id.Local)).setText(n.getLocal());

        return convertView;
    }

}
