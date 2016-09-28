package com.example.mlanie.mabibliotheque;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    private Cursor cursor;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView liste = (ListView)findViewById(R.id.listView);

        try{
            MaBaseSQLite maBaseHelper = new MaBaseSQLite(this);
            db = maBaseHelper.getWritableDatabase();
            cursor = db.query(MaBaseSQLite.getTableLivre(),
                    new String [] {MaBaseSQLite.getColonneId(), MaBaseSQLite.getCOLONNE_Titre(), MaBaseSQLite.getColonneNom(), MaBaseSQLite.getColonneAnnee()},
                    null, null, null, null, MaBaseSQLite.getColonneId()+" DESC");


            CursorAdapter listAdapter = new SimpleCursorAdapter(this, R.layout.list_item_layout,cursor,
                    new String [] {MaBaseSQLite.getCOLONNE_Titre(), MaBaseSQLite.getColonneNom(), MaBaseSQLite.getColonneAnnee()},
                    new int [] {R.id.liste_titre, R.id.liste_nom, R.id.liste_annee}, 0);

            liste.setAdapter(listAdapter);

        }catch (SQLiteException e){
            e.printStackTrace();
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //if (position == 0){ //Empêche de cliquer sur la deuxième position
                    Intent intent = new Intent(MainActivity.this,Book_Display.class);
                    intent.putExtra("BookID", (int) id);
                    startActivity(intent);
                //}
            }
        };
        liste.setOnItemClickListener(itemClickListener);
    }

   @Override
   protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
   }

    public void ChangerDePagePourCreerUneFicheDeLivre (View button){
        Intent createNewBook = new Intent(this, BookRegistration.class);
        createNewBook.putExtra("Book to modify", -1);
        this.startActivity(createNewBook);
    }

}
