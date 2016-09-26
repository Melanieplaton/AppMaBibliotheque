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

    //Bundle bundle;
    //String maValeur;
    //public ArrayList<Book> bookList = new ArrayList<Book>();

    private Cursor cursor;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView liste = (ListView)findViewById(R.id.listView);

        //maValeur = savedInstanceState.getString("key");

        //this.bundle = savedInstanceState;

        try{
            MaBaseSQLite maBaseHelper = new MaBaseSQLite(this);
            db = maBaseHelper.getWritableDatabase();
            cursor = db.query(MaBaseSQLite.getTableLivre(),
                    new String [] {MaBaseSQLite.getColonneId(), MaBaseSQLite.getCOLONNE_Titre(), MaBaseSQLite.getColonneNom(), MaBaseSQLite.getColonneAnnee()},
                    null, null, null, null, null);


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
                if (position == 0){
                    Intent intent = new Intent(MainActivity.this,Book_Display.class);
                    intent.putExtra("BookID", position);
                    startActivity(intent);
                }
            }
        };
        liste.setOnItemClickListener(itemClickListener);
        //ListView liste = (ListView)findViewById(R.id.listView);
        //ArrayAdapter<Book>adapter = new ArrayAdapter<Book>(this, android.R.layout.simple_list_item_2, getListItems(bookList));
        //BookAdapter bookAdapter = new BookAdapter(this,getListItems(bookList));
        //liste.setAdapter(bookAdapter);

    }

   @Override
   protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }


    public void ChangerDePagePourCreerUneFicheDeLivre (View button){
        Intent CreateNewBook = new Intent(this, BookRegistration.class);
        this.startActivity(CreateNewBook);
        //setContentView(R.layout.activity_book_registration);
    }

   /* public ArrayList<Book> getListItems(ArrayList<Book> listBook){
        listBook.add(new Book("Titre1", "Auteur1",2001));
        listBook.add(new Book("Titre2", "Auteur2",2002));
        listBook.add(new Book("Titre3", "Auteur3",2003));
        return listBook;
    }*/


    /*@Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent onClickItem = new Intent(this,Book_Display.class);
        onClickItem.putExtra("BookID", i);
        this.startActivity(onClickItem);

    }*/

    //public ArrayList<Book> getBookList() {
        //return bookList;
    //}

    //public void setBookList(ArrayList<Book> bookList) {
        //this.bookList = bookList;
    //}


}
