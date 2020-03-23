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
import android.widget.Toast;

public class notificacoes extends AppCompatActivity {

    DB mDbHelper;
    SQLiteDatabase db;
    Cursor c, c_notas;
    ListView list;
    //SimpleCursorAdapter adapter;
    MyCursorAdapter madapter;
    View view;
    public notificacoes(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacoes);

        mDbHelper = new DB(this);
        db = mDbHelper.getReadableDatabase();
        list = findViewById(R.id.lista);
        registerForContextMenu(list);

        fillList();

    }

    /*public void inserir(View view) {
        ContentValues cv = new ContentValues();
        cv.put(Contrato.Notas.COLUMN_TITLE, String.valueOf(findViewById(R.id.editTitulo)));
        cv.put(Contrato.Notas.COLUMN_DATA, String.valueOf(findViewById(R.id.editData)));
        cv.put(Contrato.Notas.COLUMN_ID_CIDADE, String.valueOf(findViewById(R.id.editLocal)));
        db.insert(Contrato.Notas.TABLE_NAME, null, cv);

        refresh();
    }*/

    public void fillList(){

        getCursor();

        //c = db.query(false,Contrato.Notas.TABLE_NAME, Contrato.Notas.PROJECTION, null, null, null, null, null, null);
        /*adapter  = new SimpleCursorAdapter(this,android.R.layout.two_line_list_item,c,new String[]
               {Contrato.Notas.COLUMN_TITULO, Contrato.Notas.COLUMN_LOCAL }, new int[] {android.R.id.text1, android.R.id.text2},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);*/

        madapter = new MyCursorAdapter(notificacoes.this, c);
        list.setAdapter(madapter);

    }

    public void refresh(){
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
        Intent i = new Intent(notificacoes.this, inserirNotificacao.class);
        startActivity(i);
        ContentValues cv = new ContentValues();
        db.update(Contrato.Notas.TABLE_NAME, cv, Contrato.Notas._ID + " = ?", new String[]{id+""});
        refresh();
    }


}
