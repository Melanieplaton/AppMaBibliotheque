package com.example.mlanie.mabibliotheque;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mélanie on 04/08/2016.
 */
public class MaBaseSQLite extends SQLiteOpenHelper {

    private static final String NOM_BDD = "mabibliotheque.db";
    private static final int VERSION_BDD = 1;

    private static final String TABLE_LIVRE = "table_livre";
    private static final String COLONNE_ID = "_id";
    private static final String COLONNE_Titre = "Titre";
    private static final String COLONNE_NOM = "Nom_Auteur";
    private static final String COLONNE_ANNEE = "Annee_Publication";

    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_LIVRE+ " (" + COLONNE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLONNE_Titre + " TEXT NOT NULL, " + COLONNE_NOM + " TEXT NOT NULL, " + COLONNE_ANNEE + " INTEGER );";

    public MaBaseSQLite(Context context) {
        super(context, NOM_BDD, null, VERSION_BDD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db,0,VERSION_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db,oldVersion,newVersion);

    }

    public void insertLivre (SQLiteDatabase db, Book book){
        ContentValues values = new ContentValues();
        values.put(COLONNE_Titre, book.getBookTitle());
        values.put(COLONNE_NOM, book.getAuthorName());
        values.put(COLONNE_ANNEE, book.getPublishedYear());
        db.insert(TABLE_LIVRE, null, values);
    }

    public void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion){
        if (oldVersion<=1){
            //On créé la table à partir de la requête écrite dans la variable CREATE_BDD
            db.execSQL(CREATE_BDD);
            Book book1 = new Book("titre1", "auteur1", 2015);
            Book book2 = new Book("titre2", "auteur2", 2014);
            insertLivre(db, book1);
            insertLivre(db, book2);
        }
    }

    public static String getNomBdd() {
        return NOM_BDD;
    }

    public static int getVersionBdd() {
        return VERSION_BDD;
    }

    public static String getTableLivre() {
        return TABLE_LIVRE;
    }

    public static String getColonneId() {
        return COLONNE_ID;
    }

    public static String getCOLONNE_Titre() {
        return COLONNE_Titre;
    }

    public static String getColonneNom() {
        return COLONNE_NOM;
    }

    public static String getColonneAnnee() {
        return COLONNE_ANNEE;
    }
}
