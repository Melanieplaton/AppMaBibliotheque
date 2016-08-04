package com.example.mlanie.mabibliotheque;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    //Bundle bundle;
    //String maValeur;
    public ArrayList<Book> bookList = new ArrayList<Book>();
    private Cursor cursor;
    private SQLiteDatabase maBaseSQLite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView liste = (ListView)findViewById(R.id.listView);

        //maValeur = savedInstanceState.getString("key");

        //this.bundle = savedInstanceState;

        try{
            LivresBDD maBaseHelper = new LivresBDD(this);
            maBaseSQLite = maBaseHelper.getReadableDatabase();
            cursor = maBaseSQLite.query(LivresBDD.getTableLivre(), new String [] {LivresBDD.getColonneId(), LivresBDD.getCOLONNE_Titre(), LivresBDD.getColonneNom()},
                    null, null, null, null, null);


            CursorAdapter listAdapter = new SimpleCursorAdapter(this, R.layout.list_item_layout,cursor,new String [] {LivresBDD.getCOLONNE_Titre(), LivresBDD.getColonneNom()},
                    new int [] {R.id.textView,R.id.textView2,R.id.textView3}, 0);


            liste.setAdapter(listAdapter);




        }catch (SQLiteException e){
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }


        //ListView liste = (ListView)findViewById(R.id.listView);
        //ArrayAdapter<Book>adapter = new ArrayAdapter<Book>(this, android.R.layout.simple_list_item_2, getListItems(bookList));
        //BookAdapter bookAdapter = new BookAdapter(this,getListItems(bookList));
        //liste.setAdapter(bookAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        maBaseSQLite.close();
    }

    /*@Override
    protected void onPause() {
        super.onPause();
        bundle.putString("key", maValeur);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }*/

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


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent onItemClick = new Intent(this,Book_Display.class);
        onItemClick.putExtra("BookId", i);
        this.startActivity(onItemClick);

    }

    public ArrayList<Book> getBookList() {
        return bookList;
    }

    public void setBookList(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }


}
