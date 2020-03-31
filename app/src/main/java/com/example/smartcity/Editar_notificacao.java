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

public class Editar_notificacao extends AppCompatActivity {

    Button update;
    EditText getTitulo;
    EditText getLocal;
    EditText getData;
    ListView listViewNotas;
    DB mDbHelper;
    int id;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_notificacao);
        mDbHelper = new DB(this);
        db = mDbHelper.getReadableDatabase();
        listViewNotas = findViewById(R.id.listaNotas);
        getTitulo =  findViewById(R.id.editTitulo);
        getLocal =  findViewById(R.id.editCidade);
        getData = findViewById(R.id.editData);
        update = findViewById(R.id.update);

        /*Intent intent = getIntent();
        String extraname = intent.getStringExtra("Titulo");
        String extradata = intent.getStringExtra("Data");
        String extralocal = intent.getStringExtra("id_cidade");

        getTitulo.setText(extraname);
        getData.setText(extradata);
        getLocal.setText(extralocal);*/

        getTitulo.setText("Arvore na Estrada");
        getData.setText("01/01/2020");
        getLocal.setText("Braga");

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String titulo = getTitulo.getText().toString();
                String data = getData.getText().toString();
                String local =(getLocal.getText().toString());

                if ( !titulo.equals("") && !data.equals("") && !local.equals("") && mDbHelper.UpdateNotas(id, titulo, data, local) ){
                    Toast.makeText(Editar_notificacao.this, "Adicionado á Lista ", Toast.LENGTH_LONG).show();
                    getTitulo.setText("");
                    getData.setText("");
                    getLocal.setText("");
                    mDbHelper.viewData();
                    Intent output = new Intent();
                    setResult(RESULT_OK,output);
                    finish();

                } else {
                    Toast.makeText(Editar_notificacao.this, "Faltam Campos por inserir! ", Toast.LENGTH_LONG).show();

                }


            }

        });
    }
}
