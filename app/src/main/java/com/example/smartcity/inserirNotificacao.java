package com.example.smartcity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import android.database.sqlite.SQLiteDatabase;


public class inserirNotificacao extends AppCompatActivity {

    Button add, update;
    EditText getTitulo;
    EditText getLocal;
    EditText getData;
    DB mDbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_notificacao);
        mDbHelper = new DB(this);
        db = mDbHelper.getReadableDatabase();
        final ListView listView = findViewById(R.id.lista);
        getTitulo =  findViewById(R.id.editTitulo);
        getLocal =  findViewById(R.id.editCidade);
        getData = findViewById(R.id.editData);

        add = (Button) findViewById(R.id.add);
        update = findViewById(R.id.update);



    add.setOnClickListener(new View.OnClickListener(){

    public void onClick(View v) {

        EditText editTitulo = (EditText) findViewById(R.id.editTitulo);
        EditText editData = (EditText) findViewById(R.id.editData);
        EditText editLocal = (EditText) findViewById(R.id.editCidade);
        if (editTitulo.getText().toString().equals("")){
            Toast.makeText(inserirNotificacao.this, "Preencha o campo Titulo ", Toast.LENGTH_LONG).show();
        }
        if (editData.getText().toString().equals("")){
            Toast.makeText(inserirNotificacao.this, "Preencha o campo Data ", Toast.LENGTH_LONG).show();
        }
        if (editLocal.getText().toString().equals("")){
            Toast.makeText(inserirNotificacao.this, "Preencha o campo Local ", Toast.LENGTH_LONG).show();
        }

        ContentValues cv = new ContentValues();

        cv.put(Contrato.Notas.COLUMN_TITLE, String.valueOf(findViewById(R.id.editTitulo)));
        cv.put(Contrato.Notas.COLUMN_DATA, String.valueOf(findViewById(R.id.editData)));
        cv.put(Contrato.Notas.COLUMN_ID_CIDADE, String.valueOf(findViewById(R.id.editCidade)));
        db.insert(Contrato.Notas.TABLE_NAME, null, cv);
        //ola//
        //mDbHelper.add(editTitulo.getText().toString(), editLocal.getText().toString(), editData.getText().toString());
        Intent output = new Intent();
        setResult(RESULT_OK,output);
        finish();

    }

    });

    /*update.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) { ContentValues cv = new ContentValues();

        getTitulo.setText(Contrato.Notas.COLUMN_TITLE);
        getData.setText(Contrato.Notas.COLUMN_DATA);
        getLocal.setText(Contrato.Cidade.COLUMN_NOME);
        db.insert(Contrato.Notas.TABLE_NAME, null, cv);
        }
    });*/

}
}