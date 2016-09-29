package com.example.mlanie.mabibliotheque;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import static android.R.layout.simple_spinner_item;

public class BookRegistration extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private SQLiteDatabase db;
    String typeToRecord;

    int idBookToModify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//Création des actions du spinner
        String[] arraySpinner;
        setContentView(R.layout.content_book_registration);
        arraySpinner = new String[]{
                "Policier", "Thriller", "SF", "Fantasy", "Romance", "Historique", "BD", "Manga", "Autre"
        };
        Spinner s = (Spinner) findViewById(R.id.spinnerGenre);
        s.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                simple_spinner_item, arraySpinner);
        s.setAdapter(adapter);

        idBookToModify = (int) getIntent().getExtras().get("Book to modify");
        if (idBookToModify != -1){
            modifyBook(idBookToModify);
        }
    }

    public void saveTheBook (View Button){
        EditText titreFromUser = (EditText) findViewById(R.id.Writing_Book_Name);
        EditText nomAuteurFromUser = (EditText) findViewById(R.id.Writing_Author_Name);
        EditText anneePublicationFromUser = (EditText) findViewById(R.id.Writing_Date);
        EditText authorFirstNameFromUser = (EditText) findViewById(R.id.Writing_Author_First_Name);
        EditText homeEditionFromUser = (EditText) findViewById(R.id.Writing_Home_Edition);
        EditText pagesFromUser = (EditText) findViewById(R.id.Writing_Page_Number);
        RatingBar ratingFromUser = (RatingBar) findViewById(R.id.ratingBar);
        EditText reviewFromUser = (EditText) findViewById(R.id.Writing_Your_Opinion);
        EditText summaryFromUser = (EditText) findViewById(R.id.Writing_The_Summary);
        Spinner typeFromUser = (Spinner) findViewById(R.id.spinnerGenre);


        String titreAEnregistrer = titreFromUser.getText().toString();
        String nomAuteurAEnregistrer = nomAuteurFromUser.getText().toString();
        String anneePublicationAEnregistrer = anneePublicationFromUser.getText().toString();
        String authorFirstNameToRecord = authorFirstNameFromUser.getText().toString();
        String homeEditiontoRecord = homeEditionFromUser.getText().toString();
        String pagesToRecord = pagesFromUser.getText().toString();
        Float ratingToRecord = ratingFromUser.getRating();
        String reviewToRecord = reviewFromUser.getText().toString();
        String summaryToRecord = summaryFromUser.getText().toString();

        int annee;
        if (anneePublicationAEnregistrer.equals("")) {
            annee = 1985;
        } else {
            annee = Integer.valueOf(anneePublicationAEnregistrer);
        }

        int pages;
        if (pagesToRecord.equals("")){
            pages = 0;
        }else {
            pages =  Integer.valueOf(pagesToRecord.toString());
        }

        if ((titreAEnregistrer.equals("")) || (nomAuteurAEnregistrer.equals(""))){

            Toast toast = Toast.makeText(this, "Impossible à enregistrer, titre et nom de l'auteur à remplir", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            Book book = new Book(titreAEnregistrer, nomAuteurAEnregistrer, annee, authorFirstNameToRecord, homeEditiontoRecord, pages, ratingToRecord, reviewToRecord, summaryToRecord, typeToRecord);

            MaBaseSQLite livresBDD = new MaBaseSQLite(this);
            db = livresBDD.getWritableDatabase();
            if (idBookToModify == -1) {
                livresBDD.insertLivre(db, book);
                Toast toast = Toast.makeText(this, "Livre enregistré", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                livresBDD.updateBook(db, book, idBookToModify);
                Toast toast = Toast.makeText(this, "Livre modifié", Toast.LENGTH_SHORT);
                toast.show();
            }
            livresBDD.close();

            Intent returnList = new Intent(this, MainActivity.class);
            this.startActivity(returnList);
        }
    }


    public void onItemSelected (AdapterView<?> parent, View view, int pos, long id){
            typeToRecord = parent.getItemAtPosition(pos).toString();
        }

    public void onNothingSelected (AdapterView<?> parent){
        typeToRecord = "";
    }

    public void modifyBook(int bookIdToModify){
        MaBaseSQLite livresBDD = new MaBaseSQLite(this);
        db =livresBDD.getWritableDatabase();
        Cursor cursor = db.query(MaBaseSQLite.getTableLivre(), new String [] {MaBaseSQLite.getColonneId(), MaBaseSQLite.getCOLONNE_Titre(), MaBaseSQLite.getColonneNom(), MaBaseSQLite.getColonneAnnee(),MaBaseSQLite.getColumnFirstname(), MaBaseSQLite.getColumnEditionhome(), MaBaseSQLite.getColumnPages(), MaBaseSQLite.getColumnRating(), MaBaseSQLite.getColumnReview(), MaBaseSQLite.getColumnSummary(), MaBaseSQLite.getColumnType()},
                MaBaseSQLite.getColonneId()+" = ? ", new String [] {Integer.toString(bookIdToModify)}, null, null, null);
        if (cursor.moveToFirst()){
            EditText titreFromUser = (EditText) findViewById(R.id.Writing_Book_Name);
            EditText nomAuteurFromUser = (EditText) findViewById(R.id.Writing_Author_Name);
            EditText anneePublicationFromUser = (EditText) findViewById(R.id.Writing_Date);
            EditText authorFirstNameFromUser = (EditText) findViewById(R.id.Writing_Author_First_Name);
            EditText homeEditionFromUser = (EditText) findViewById(R.id.Writing_Home_Edition);
            EditText pagesFromUser = (EditText) findViewById(R.id.Writing_Page_Number);
            RatingBar ratingFromUser = (RatingBar) findViewById(R.id.ratingBar);
            EditText reviewFromUser = (EditText) findViewById(R.id.Writing_Your_Opinion);
            EditText summaryFromUser = (EditText) findViewById(R.id.Writing_The_Summary);
            Spinner typeFromUser = (Spinner) findViewById(R.id.spinnerGenre);

            titreFromUser.setText(cursor.getString(1));
            nomAuteurFromUser.setText(cursor.getString(2)) ;
            anneePublicationFromUser.setText(Integer.toString(cursor.getInt(3))) ;
            authorFirstNameFromUser.setText(cursor.getString(4)) ;
            homeEditionFromUser.setText(cursor.getString(5));
            pagesFromUser.setText(Integer.toString(cursor.getInt(6))) ;
            ratingFromUser.setRating(cursor.getFloat(7));
            reviewFromUser.setText(cursor.getString(8));
            summaryFromUser.setText(cursor.getString(9));
        }
        cursor.close();
        livresBDD.close();
    }

}


