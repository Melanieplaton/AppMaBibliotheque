package com.example.mlanie.mabibliotheque;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class Book_Display extends AppCompatActivity {

//page qui affiche les details d'un livre
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book__display);

        int listPosition= (Integer)getIntent().getExtras().get("BookID");

        TextView titre = (TextView) findViewById(R.id.display_titre);
        TextView nomAuteur = (TextView) findViewById(R.id.display_nom);
        TextView anneeParution = (TextView) findViewById(R.id.display_annee);



        try{
            MaBaseSQLite maBaseHelper = new MaBaseSQLite(this);
            SQLiteDatabase maBaseSQLite = maBaseHelper.getReadableDatabase();
            Cursor cursor = maBaseSQLite.query(maBaseHelper.getTableLivre(), new String [] {maBaseHelper.getColonneId(), maBaseHelper.getCOLONNE_Titre(), maBaseHelper.getColonneNom(), maBaseHelper.getColonneAnnee()},
                    maBaseHelper.getColonneId()+" = ? ", new String [] {Integer.toString(listPosition)}, null, null, null);

            if (cursor.moveToFirst()){
                nomAuteur.setText(cursor.getString(2));
                titre.setText(cursor.getString(1));
                anneeParution.setText(Integer.toString(cursor.getInt(3)));

            }// a faire avec les autres attributs de la classe book
            cursor.close();
            maBaseSQLite.close();

        }catch (SQLiteException e){
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

}
