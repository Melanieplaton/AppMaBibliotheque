package com.example.mlanie.mabibliotheque;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import static android.R.layout.simple_spinner_item;

public class BookRegistration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*EditText titreFromUser = (EditText) findViewById(R.id.Writing_Book_Name);
        EditText nomAuteurFromUser = (EditText) findViewById(R.id.Writing_Author_Name);
        EditText anneePublicationFromUser = (EditText) findViewById(R.id.Writing_Date);

        String titreAEnregistrer = titreFromUser.getText().toString();
        String nomAuteurAEnregistrer = nomAuteurFromUser.getText().toString();
        String anneePublicationAEnregistrer = anneePublicationFromUser.getText().toString();

        int annee;
        if (anneePublicationAEnregistrer.equals("")) {
            annee = 1985;
        } else {
            annee = Integer.valueOf(anneePublicationAEnregistrer);
        }


        Book book = new Book(titreAEnregistrer, nomAuteurAEnregistrer, annee);

        LivresBDD livresBDD = new LivresBDD(this);
        livresBDD.open();
        livresBDD.insertLivre(book);*/


//Création des actions du spinner
        String[] arraySpinner;
        setContentView(R.layout.content_book_registration);
        arraySpinner = new String[]{
                "Policier", "Thriller", "SF", "Fantasy", "Romance", "Historique", "BD", "Manga", "Autre"
        };
        Spinner s = (Spinner) findViewById(R.id.spinnerGenre);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                simple_spinner_item, arraySpinner);
        s.setAdapter(adapter);

    }

    public void saveTheBook (View Button){
        EditText titreFromUser = (EditText) findViewById(R.id.Writing_Book_Name);
        EditText nomAuteurFromUser = (EditText) findViewById(R.id.Writing_Author_Name);
        EditText anneePublicationFromUser = (EditText) findViewById(R.id.Writing_Date);

        String titreAEnregistrer = titreFromUser.getText().toString();
        String nomAuteurAEnregistrer = nomAuteurFromUser.getText().toString();
        String anneePublicationAEnregistrer = anneePublicationFromUser.getText().toString();

        int annee;
        if (anneePublicationAEnregistrer.equals("")) {
            annee = 1985;
        } else {
            annee = Integer.valueOf(anneePublicationAEnregistrer);
        }


        Book book = new Book(titreAEnregistrer, nomAuteurAEnregistrer, annee);

        LivresBDD livresBDD = new LivresBDD(this);
        livresBDD.open();
        livresBDD.insertLivre(book);
        livresBDD.close();

        Intent returnList = new Intent (this, MainActivity.class);
        this.startActivity(returnList);

    }

}


