package com.example.smartcity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class notificacoes extends AppCompatActivity {

    DB mDbHelper;
    SQLiteDatabase db;
    Cursor c, c_notas;
    ListView lista;
    SimpleCursorAdapter adapter;
    View view;

    public notificacoes(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacoes);
    }


    public void fillLista(){
        ArrayList<Notas> arrayNotas = new ArrayList<>();
        arrayNotas.add(new Notas ("Buracao na estrada", "20/02/20", "Braga"));

        CustomArrayAdapter itensAdapter =
                new CustomArrayAdapter(this, arrayNotas);
        ((ListView) findViewById(R.id.listview)).setAdapter(itensAdapter);


    }


    public void inserir(View view) {
        Intent i = new Intent(notificacoes.this, inserirNotificacao.class);
        startActivity(i);
    }
}
