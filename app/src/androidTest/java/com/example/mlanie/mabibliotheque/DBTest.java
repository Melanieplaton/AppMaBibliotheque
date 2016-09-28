package com.example.mlanie.mabibliotheque;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.util.Log;
import android.widget.RatingBar;


/**
 * Created by Mélanie on 09/09/2016.
 */

public class DBTest extends AndroidTestCase {

    private static String bookTitle = "Le passage";
    private static String bookAuthor = "Cronin";
    private static int bookYear = 2010;
    private static long bookDBAssignId;
    private static String bookAuthorFirstName = "Justin";
    private static String bookHomeEdition = "Maison";
    private static int bookPages = 900;
    private static float bookRating = (float) 4.0;
    private static String bookReview = "Très Bien";
    private static String bookSummary = "Viruls";
    private static String bookType = "Thriller";


    public void testDropDB(){
        MaBaseSQLite maBaseSQLite = new MaBaseSQLite(mContext);
        SQLiteDatabase db = maBaseSQLite.getWritableDatabase();
        assertTrue(mContext.deleteDatabase(MaBaseSQLite.getNomBdd()));
        Log.e("testDropBD", "pass");
    }


    public void testCreateDB(){
        MaBaseSQLite maBaseSQLite = new MaBaseSQLite(mContext);
        SQLiteDatabase db = maBaseSQLite.getWritableDatabase();
        assertTrue(db.isOpen());
        db.close();
        Log.e("testCreateDB", "pass");
    }


    public void testInsertData(){
        MaBaseSQLite maBaseSQLite = new MaBaseSQLite(mContext);
        SQLiteDatabase db = maBaseSQLite.getWritableDatabase();
        bookTitle = "Le passage";
        bookAuthor = "Cronin";
        bookYear = 2010;
        bookAuthorFirstName = "Justin";
        bookHomeEdition = "Maison";
        bookPages = 900;
        bookRating = (float) 4.0;
        bookReview = "Très Bien";
        bookSummary = "Viruls";
        bookType = "Thriller";


        ContentValues contentValues = new ContentValues();
        contentValues.put(MaBaseSQLite.getCOLONNE_Titre(),bookTitle);
        contentValues.put(MaBaseSQLite.getColonneNom(),bookAuthor);
        contentValues.put(MaBaseSQLite.getColonneAnnee(),bookYear);
        contentValues.put(MaBaseSQLite.getColumnFirstname(), bookAuthorFirstName);
        contentValues.put(MaBaseSQLite.getColumnEditionhome(), bookHomeEdition);
        contentValues.put(MaBaseSQLite.getColumnPages(), bookPages);
        contentValues.put(MaBaseSQLite.getColumnRating(), bookRating);
        contentValues.put(MaBaseSQLite.getColumnReview(), bookReview);
        contentValues.put(MaBaseSQLite.getColumnSummary(), bookSummary);
        contentValues.put(MaBaseSQLite.getColumnType(), bookType);
        bookDBAssignId = db.insert(MaBaseSQLite.getTableLivre(), null, contentValues);
        assertTrue(bookDBAssignId != -1);
        Log.e("testInsertData", "pass" + bookDBAssignId);

    }

//Test pour récupérer les id
    public void testIsDataCorrectInDB(){
        MaBaseSQLite maBaseSQLite = new MaBaseSQLite(mContext);
        SQLiteDatabase db = maBaseSQLite.getWritableDatabase();
        Cursor cursor = db.query(MaBaseSQLite.getTableLivre(), null, null, null, null, null, null);
        cursor.moveToFirst();
        assertTrue(cursor.moveToFirst());


        int idColumnIndex = cursor.getColumnIndex(MaBaseSQLite.getColonneId());
        int dbId = cursor.getInt(idColumnIndex);

        int bookTitleColumnIndex = cursor.getColumnIndex(MaBaseSQLite.getCOLONNE_Titre());
        String dbBookTitle = cursor.getString(bookTitleColumnIndex);

        int bookAuthorColumnIndex = cursor.getColumnIndex(MaBaseSQLite.getColonneNom());
        String dbBookAuthor = cursor.getString(bookAuthorColumnIndex);

        int bookYearColumnIndex = cursor.getColumnIndex(MaBaseSQLite.getColonneAnnee());
        int dbBookYear = cursor.getInt(bookYearColumnIndex);

        assertEquals(bookDBAssignId, dbId);
        assertEquals(bookTitle, dbBookTitle);
        assertEquals(bookAuthor, dbBookAuthor);
        assertEquals(bookYear, dbBookYear);
        Log.e("testIsDataCorrectInDB", "pass");
        cursor.close();

    }
}
