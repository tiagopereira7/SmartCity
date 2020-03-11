package com.example.smartcity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class fragment_list extends Fragment {

    DB mDbHelper;
    SQLiteDatabase db;
    Cursor c, c_notas;
    ListView lista;
    SimpleCursorAdapter adapter;
    View view;


    public fragment_list() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mDbHelper = new DB(getActivity());
        db = mDbHelper.getReadableDatabase();

        view = inflater.inflate(R.layout.fragment_list, container, false);
        lista = (ListView) view.findViewById(R.id.listview);
        registerForContextMenu(lista);
        preencheLista();


        Button b = (Button) view.findViewById(R.id.btnInserir);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentValues cv = new ContentValues();
                cv.put(Contrato.Notas.COLUMN_TITULO, "Buraco na estrada");
                cv.put(Contrato.Notas.COLUMN_DATA, "20/01/20");
                cv.put(Contrato.Notas.COLUMN_LOCAL, "Braga");
                db.insert(Contrato.Notas.TABLE_NAME, null,cv);

                refresh();
            }
        });

        return view;
    }

    private void preencheLista(){
        c = db.query(false,Contrato.Notas.TABLE_NAME, Contrato.Notas.PROJECTION, null, null, null, null, null, null);

        adapter  = new SimpleCursorAdapter(getActivity(),android.R.layout.two_line_list_item,c,new String[] {Contrato.Notas.COLUMN_TITULO, Contrato.Notas.COLUMN_DATA, Contrato.Notas.COLUMN_LOCAL }, new int[] {android.R.id.text1, android.R.id.text2},SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        lista.setAdapter(adapter);
    }



    public void refresh(){

        adapter.swapCursor(c);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.getActivity().onCreateContextMenu(menu,v,menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_noti,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int itemPosition = info.position;
        c.moveToPosition(itemPosition);
        int id_noti = c.getInt(c.getColumnIndex(Contrato.Notas._ID));



        switch (item.getItemId()){
            case R.id.delete:
                deleteFromBD(id_noti);
                Toast.makeText(getActivity(), "Removido com sucesso!!!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.update:
                updateDB(id_noti);
                Toast.makeText(getActivity(), "Atualizado com sucesso!!!", Toast.LENGTH_SHORT).show();

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
        db.update(Contrato.Notas.TABLE_NAME, cv, Contrato.Notas._ID + " = ?", new String[]{id+""});
        adapter.notifyDataSetChanged();
        refresh();
    }

}
