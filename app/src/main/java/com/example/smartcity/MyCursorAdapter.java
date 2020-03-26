package com.example.smartcity;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;


public class MyCursorAdapter extends CursorAdapter {

    private Context mContext;
    private int id;
    private Cursor mCursor;


    public MyCursorAdapter(Context context, Cursor c){
        super(context, c, 0);
        mContext = context;
        mCursor = c;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.layout_linha, parent, false);
    }

    @Override
    public void bindView(View mView, Context context, Cursor cursor) {
        TextView text1 = (TextView) mView.findViewById(R.id.titulo);
        TextView text2 = (TextView) mView.findViewById(R.id.data);
        TextView text3 = (TextView) mView.findViewById(R.id.cidade);
        text1.setText(mCursor.getString(cursor.getColumnIndexOrThrow(Contrato.Notas.COLUMN_TITLE)));
        text2.setText(mCursor.getString(cursor.getColumnIndexOrThrow(Contrato.Notas.COLUMN_DATA)));
        text3.setText(mCursor.getString(cursor.getColumnIndexOrThrow(Contrato.Cidade.COLUMN_NOME)));


    }
}
