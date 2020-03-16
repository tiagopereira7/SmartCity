package com.example.smartcity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class notificacoes extends AppCompatActivity {
    DB mDbHelper;
    SQLiteDatabase db;
    Cursor c, c_notas;
    ListView lista;
    SimpleCursorAdapter adapter;
    View view;
    private int start = 1;

    public notificacoes(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacoes);

        mDbHelper = new DB(this);
//        db = mDbHelper.getReadableDatabase();
        lista = (ListView) findViewById(R.id.lista);
        //registerForContextMenu(lista);

        fillLista();
    }


    public void fillLista(){
        c = db.query(false,Contrato.Notas.TABLE_NAME, Contrato.Notas.PROJECTION,
                null, null, null, null, null, null);

        adapter  = new SimpleCursorAdapter(this,android.R.layout.two_line_list_item,c,new String[]
                {Contrato.Notas.COLUMN_TITULO, Contrato.Notas.COLUMN_LOCAL }, new int[] {android.R.id.text1, android.R.id.text2},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        lista.setAdapter(adapter);

    }


    public void inserir(View view) {
        Intent i = new Intent(notificacoes.this, inserirNotificacao.class);
        startActivityForResult(i, start);
    }

    public void refresh(){

        adapter.swapCursor(c);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int itemPosition = info.position;
        c.moveToPosition(itemPosition);
        int id_Notas = c.getInt(c.getColumnIndex(Contrato.Notas._ID));



        switch (item.getItemId()){
            case R.id.delete:
                deleteFromBD(id_Notas);
                Toast.makeText(this, "Removido com sucesso!!!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.update:
                updateDB(id_Notas);
                Toast.makeText(this, "Atualizado com sucesso!!!", Toast.LENGTH_SHORT).show();

                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void deleteFromBD(int id){
        db.delete(Contrato.Notas.TABLE_NAME, Contrato.Notas._ID + " = ?", new String[]{id+""});
        adapter.notifyDataSetChanged();
        refresh();
    }

    private void updateDB(int id){
        ContentValues cv = new ContentValues();
        cv.put(Contrato.Notas.COLUMN_LOCAL, "Braga");
        db.update(Contrato.Notas.TABLE_NAME, cv, Contrato.Notas._ID + " = ?", new String[]{id+""});
        adapter.notifyDataSetChanged();
        refresh();
    }

}
