package com.example.mlanie.mabibliotheque;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Mélanie on 04/08/2016.
 */
public class LivresBDD extends MaBaseSQLite {
    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "mabibliotheque.db";

    private static final String TABLE_LIVRE = "table_livre";
    private static final String COLONNE_ID = "_id";
    private static final String COLONNE_Titre = "Titre";
    private static final String COLONNE_NOM = "Nom_Auteur";
    private static final String COLONNE_ANNEE = "Annee_Publication";

    private SQLiteDatabase bdd;

    private MaBaseSQLite maBaseSQLite;

    public LivresBDD (Context context){
        super(context,NOM_BDD, null, VERSION_BDD);
        maBaseSQLite = new MaBaseSQLite(context, NOM_BDD, null, VERSION_BDD);
        Book book = new Book ("Minion", "Moi", 1996);
        open();
        long var = insertLivre(book);
        close();
        //context.deleteDatabase(NOM_BDD);
    }

    public void open(){
        bdd = maBaseSQLite.getWritableDatabase();
    }

    public void close(){
        bdd.close();
    }

    public SQLiteDatabase getBdd(){
        return bdd;
    }

    public long insertLivre (Book book){
        ContentValues values = new ContentValues();
        values.put(COLONNE_Titre, book.getBookTitle());
        values.put(COLONNE_NOM, book.getAuthorName());
        values.put(COLONNE_ANNEE, book.getPublishedYear());
        return bdd.insert(TABLE_LIVRE, null, values);
    }

    public int updateLivre (int id, Book book){
        ContentValues values = new ContentValues();
        values.put(COLONNE_Titre, book.getBookTitle());
        values.put(COLONNE_NOM, book.getAuthorName());
        values.put(COLONNE_ANNEE, book.getPublishedYear());
        return bdd.update(TABLE_LIVRE, values, COLONNE_ID+" = " +id, null);

    }

    public int removeLivreWithId (int id){
        return bdd.delete(TABLE_LIVRE, COLONNE_ID + " = " +id, null);
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

    public static String getTableLivre() {
        return TABLE_LIVRE;
    }

    public MaBaseSQLite getMaBaseSQLite() {
        return maBaseSQLite;
    }

    public void setMaBaseSQLite(MaBaseSQLite maBaseSQLite) {
        this.maBaseSQLite = maBaseSQLite;
    }

    public void setBdd(SQLiteDatabase bdd) {
        this.bdd = bdd;
    }

    public static String getNomBdd() {
        return NOM_BDD;
    }

    public static int getVersionBdd() {
        return VERSION_BDD;
    }

}
