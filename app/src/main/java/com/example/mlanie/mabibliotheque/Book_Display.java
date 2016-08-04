package com.example.mlanie.mabibliotheque;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Book_Display extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book__display);

        int listPosition= getIntent().getExtras().getInt("BookId");

        TextView titre = (TextView) findViewById(R.id.textView);
        TextView nomAuteur = (TextView) findViewById(R.id.textView2);
        TextView anneeParution = (TextView) findViewById(R.id.textView3);

        try{
            LivresBDD maBaseHelper = new LivresBDD(this);
            SQLiteDatabase maBaseSQLite = maBaseHelper.getReadableDatabase();
            Cursor cursor = maBaseSQLite.query(LivresBDD.getTableLivre(), new String [] {LivresBDD.getColonneId(), LivresBDD.getCOLONNE_Titre(), LivresBDD.getColonneNom()},
                    LivresBDD.getColonneId()+" = " +listPosition, null, null, null, null);

            if (cursor.moveToFirst()){
                nomAuteur.setText(cursor.getString(2));
                titre.setText(cursor.getString(1));
                anneeParution.setText(cursor.getInt(3));
            }// a faire avec les autres attributs de la classe book
            cursor.close();
            maBaseSQLite.close();

        }catch (SQLiteException e){
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }





    }

}
