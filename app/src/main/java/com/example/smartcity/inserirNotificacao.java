package com.example.smartcity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class inserirNotificacao extends AppCompatActivity {


    Button add, update;
    EditText getTitulo;
    EditText getLocal;
    EditText getData;
    ListView listViewNotas;
    DB mDbHelper;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_notificacao);
        mDbHelper = new DB(this);
        db = mDbHelper.getReadableDatabase();
        listViewNotas = findViewById(R.id.listaNotas);
        getTitulo =  findViewById(R.id.editTitulo);
        getLocal =  findViewById(R.id.editCidade);
        getData = findViewById(R.id.editData);
        add = (Button) findViewById(R.id.add);


    add.setOnClickListener(new View.OnClickListener(){

    public void onClick(View v) {

        String titulo = getTitulo.getText().toString();
        String data = getData.getText().toString();
        String local =(getLocal.getText().toString());

        if ( !titulo.equals("") && !data.equals("") && !local.equals("") && mDbHelper.addNotas(titulo, data, local) ){
            Toast.makeText(inserirNotificacao.this, "Adicionado á Lista ", Toast.LENGTH_LONG).show();
            getTitulo.setText("");
            getData.setText("");
            getLocal.setText("");
            mDbHelper.viewData();
            Intent output = new Intent();
            setResult(RESULT_OK,output);
            finish();

        } else {
            Toast.makeText(inserirNotificacao.this, "Faltam Campos por inserir! ", Toast.LENGTH_LONG).show();

        }


    }

    });



}

}