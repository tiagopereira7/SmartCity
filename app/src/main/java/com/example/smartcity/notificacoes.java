package com.example.smartcity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class notificacoes extends AppCompatActivity {

    DB mDbHelper;
    SQLiteDatabase db;
    Cursor c, c_notas;
    ListView listNotas;
    MyCursorAdapter madapter;
    ArrayAdapter adapter;
    ArrayList<String> listItem;
    public notificacoes(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacoes);
        mDbHelper = new DB(this);
        db = mDbHelper.getReadableDatabase();
        listNotas = findViewById(R.id.listaNotas);
        listItem = new ArrayList<>();
        registerForContextMenu(listNotas);

        fillList();

    }

    private void viewData() {
        db = mDbHelper.getReadableDatabase();
        Cursor cursor = mDbHelper.viewData();
        if(cursor.getCount()==0){
            Toast.makeText(this, "no Data", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                listItem.add(cursor.getString(1));
            }
            adapter = new ArrayAdapter<>(this, R.layout.layout_linha, listItem);
            listNotas.setAdapter(madapter);
        }
    }


    public void fillList(){
        viewData();
        getCursor();
        madapter = new MyCursorAdapter(notificacoes.this, c);
        listNotas.setAdapter(madapter);

    }

    public void refresh(){
        viewData();
        getCursor();
        madapter.swapCursor(c);
    }

    private void getCursor(){
        String sql = "select " + Contrato.Notas.TABLE_NAME + "." +
                Contrato.Notas._ID + "," +
                Contrato.Notas.COLUMN_TITLE + "," +
                Contrato.Notas.COLUMN_DATA + "," +
                Contrato.Cidade.COLUMN_NOME  + " FROM " +
                Contrato.Notas.TABLE_NAME + "," + Contrato.Cidade.TABLE_NAME+
                " WHERE " + Contrato.Notas.COLUMN_ID_CIDADE + "=" +
                Contrato.Cidade.TABLE_NAME + "." + Contrato.Cidade._ID;

        c = db.rawQuery(sql,null);
    }


    public void inserir(View view) {
        Intent i = new Intent(notificacoes.this, inserirNotificacao.class);
        int start = 1;
        startActivityForResult(i, start);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);
        MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.menu_noti,menu);
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
                Toast.makeText(this, "Removido com sucesso!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.update:
                updateDB(id_Notas);
                Toast.makeText(this, "Atualizado com sucesso!", Toast.LENGTH_SHORT).show();

                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void deleteFromBD(int id){
        db.delete(Contrato.Notas.TABLE_NAME, Contrato.Notas._ID + " = ?", new String[]{id+""});
        refresh();
    }

    private void updateDB(int id){
        Intent i = new Intent(notificacoes.this, Editar_notificacao.class);
        startActivity(i);
        refresh();

    }


}
