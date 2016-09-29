package com.example.mlanie.mabibliotheque;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class Book_Display extends AppCompatActivity {

    int listPosition;
    MaBaseSQLite maBaseHelper = new MaBaseSQLite(this);

//page qui affiche les details d'un livre
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book__display);

        listPosition= (int) getIntent().getExtras().get("BookID");

        TextView titre = (TextView) findViewById(R.id.display_titre);
        TextView nomAuteur = (TextView) findViewById(R.id.display_nom);
        TextView anneeParution = (TextView) findViewById(R.id.display_annee);
        TextView authorFirstName = (TextView) findViewById(R.id.display_firstName);
        TextView homeEdition = (TextView) findViewById(R.id.display_homeEdition);
        TextView pages= (TextView) findViewById(R.id.display_pages);
        TextView summary = (TextView) findViewById(R.id.display_summary);
        TextView review = (TextView) findViewById(R.id.display_review);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.display_ratingBar);
        TextView type = (TextView) findViewById(R.id.display_type);

        try{
            MaBaseSQLite maBaseHelper = new MaBaseSQLite(this);
            SQLiteDatabase maBaseSQLite = maBaseHelper.getReadableDatabase();
            Cursor cursor = maBaseSQLite.query(MaBaseSQLite.getTableLivre(), new String [] {MaBaseSQLite.getColonneId(), MaBaseSQLite.getCOLONNE_Titre(), MaBaseSQLite.getColonneNom(), MaBaseSQLite.getColonneAnnee(), MaBaseSQLite.getColumnFirstname(), MaBaseSQLite.getColumnEditionhome(), MaBaseSQLite.getColumnPages(), MaBaseSQLite.getColumnRating(), MaBaseSQLite.getColumnReview(), MaBaseSQLite.getColumnSummary(), MaBaseSQLite.getColumnType()},
                    MaBaseSQLite.getColonneId()+" = ? ", new String [] {Integer.toString(listPosition)}, null, null, null);

            if (cursor.moveToFirst()){
                nomAuteur.setText(cursor.getString(2));
                titre.setText(cursor.getString(1));
                anneeParution.setText(Integer.toString(cursor.getInt(3)));
                authorFirstName.setText(cursor.getString(4));
                homeEdition.setText(cursor.getString(5));
                pages.setText(Integer.toString(cursor.getInt(6)));
                ratingBar.setRating(cursor.getFloat(7));
                review.setText(cursor.getString(8));
                summary.setText(cursor.getString(9));
                type.setText(cursor.getString(10));
            }
            cursor.close();
            maBaseSQLite.close();

        }catch (SQLiteException e){
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void onClickedDeleteButton (View button){
        AlertDialog.Builder alertDialogBuilder= new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Etes-vous sûr de vouloir supprimer ce livre?");
        alertDialogBuilder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SQLiteDatabase maBaseSQLite = maBaseHelper.getReadableDatabase();
                int numberDeletedRow = maBaseHelper.deleteItemInDatabase(maBaseSQLite, listPosition);
                if (numberDeletedRow == 1){
                    Toast toast = Toast.makeText(Book_Display.this, "Livre supprimé", Toast.LENGTH_SHORT);
                    toast.show();
                }
                maBaseSQLite.close();
                Intent goBackToList = new Intent (Book_Display.this, MainActivity.class);
                Book_Display.this.startActivity(goBackToList);
            }
        });

        alertDialogBuilder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               dialog.cancel();
            }
        });
       AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void onClickedModifyButton (View button){
        Intent goBackToRegistrationPage = new Intent (this, BookRegistration.class);
        goBackToRegistrationPage.putExtra("Book to modify", listPosition);
        this.startActivity(goBackToRegistrationPage);
    }

}
