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
    private static final int VERSION_BDD = 2;

    private static final String TABLE_LIVRE = "table_livre";
    private static final String COLONNE_ID = "_id";
    private static final String COLONNE_Titre = "Titre";
    private static final String COLONNE_NOM = "Nom_Auteur";
    private static final String COLONNE_ANNEE = "Annee_Publication";
    private static final String COLUMN_FIRSTNAME= "Author_First_Name";
    private static final String COLUMN_EDITIONHOME="Edition_home";
    private static final String COLUMN_PAGES = "Page_Number";
    private static final String COLUMN_TYPE = "Book_Type";
    private static final String COLUMN_SUMMARY = "Book_Summary";
    private static final String COLUMN_REVIEW = "Book_Review";
    private static final String COLUMN_RATING = "Rating";

    //creation of the first version of the DB with only 3 columns
    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_LIVRE + " (" + COLONNE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + COLONNE_Titre + " TEXT NOT NULL, " + COLONNE_NOM + " TEXT NOT NULL, " + COLONNE_ANNEE + " INTEGER );";

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

    public void insertBook(SQLiteDatabase db, Book book){
        ContentValues values = new ContentValues();
        values.put(COLONNE_Titre, book.getBookTitle());
        values.put(COLONNE_NOM, book.getAuthorName());
        values.put(COLONNE_ANNEE, book.getPublishedYear());
        values.put(COLUMN_FIRSTNAME, book.getAuthorFirstName());
        values.put(COLUMN_EDITIONHOME, book.getHomeEdition());
        values.put(COLUMN_PAGES, book.getPages());
        values.put(COLUMN_REVIEW, book.getReview());
        values.put(COLUMN_SUMMARY, book.getBookSummary());
        values.put(COLUMN_RATING, book.getRating());
        values.put(COLUMN_TYPE, book.getBookType());
        db.insert(TABLE_LIVRE, null, values);
    }

    public void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion){
        if (oldVersion<=1){
            //creation of the DB
            db.execSQL(CREATE_BDD);

        }
        if (oldVersion<2){
            //update of the database with all the attributes of a book
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_LIVRE);
            db.execSQL("CREATE TABLE "+TABLE_LIVRE+ " (" + COLONNE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + COLONNE_Titre + " TEXT NOT NULL, " + COLONNE_NOM + " TEXT NOT NULL, " + COLONNE_ANNEE + " INTEGER, "+ COLUMN_FIRSTNAME+ " TEXT, "+COLUMN_EDITIONHOME+ " TEXT, " +COLUMN_PAGES+ " INTEGER, "+COLUMN_RATING+ " TEXT, "+COLUMN_REVIEW+ " TEXT, "+COLUMN_SUMMARY+" TEXT, "+COLUMN_TYPE+ " TEXT);");

            //example of book
            Book book3 = new Book ("Le passage","Cronin", 2010, "Justin", "Maison", 900, (float) 4.0, "Très Bien", "Viruls", "Thriller");
            insertBook(db, book3);
        }

    }

    public int deleteItemInDatabase (SQLiteDatabase db, int bookId ){
        int nbDeletedRow = db.delete(getTableLivre(), getColonneId() + " = "+ bookId, null);
        return nbDeletedRow;

    }

    public int updateBook (SQLiteDatabase db, Book book, int bookId){
        ContentValues values = new ContentValues();
        values.put(COLONNE_Titre, book.getBookTitle());
        values.put(COLONNE_NOM, book.getAuthorName());
        values.put(COLONNE_ANNEE, book.getPublishedYear());
        values.put(COLUMN_FIRSTNAME, book.getAuthorFirstName());
        values.put(COLUMN_EDITIONHOME, book.getHomeEdition());
        values.put(COLUMN_PAGES, book.getPages());
        values.put(COLUMN_REVIEW, book.getReview());
        values.put(COLUMN_SUMMARY, book.getBookSummary());
        values.put(COLUMN_RATING, book.getRating());
        values.put(COLUMN_TYPE, book.getBookType());
        int nbUpdatedRow = db.update(TABLE_LIVRE, values, getColonneId() + " = "+ bookId, null);
        return nbUpdatedRow;

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

    public static String getCreateBdd() {
        return CREATE_BDD;
    }

    public static String getColumnFirstname() {
        return COLUMN_FIRSTNAME;
    }

    public static String getColumnEditionhome() {
        return COLUMN_EDITIONHOME;
    }

    public static String getColumnPages() {
        return COLUMN_PAGES;
    }

    public static String getColumnType() {
        return COLUMN_TYPE;
    }

    public static String getColumnSummary() {
        return COLUMN_SUMMARY;
    }

    public static String getColumnReview() {
        return COLUMN_REVIEW;
    }

    public static String getColumnRating() {
        return COLUMN_RATING;
    }
}
