package com.example.mlanie.mabibliotheque;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.util.Log;


/**
 * Created by Mélanie on 09/09/2016.
 */

public class DBTest extends AndroidTestCase {

    private static String bookTitle;
    private static String bookAuthor;
    private static int bookYear;
    private static long bookDBAssignId;



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

        ContentValues contentValues = new ContentValues();
        contentValues.put(MaBaseSQLite.getCOLONNE_Titre(),bookTitle);
        contentValues.put(MaBaseSQLite.getColonneNom(),bookAuthor);
        contentValues.put(MaBaseSQLite.getColonneAnnee(),bookYear);
        bookDBAssignId = db.insert(MaBaseSQLite.getNomBdd(), null, contentValues);
        assertTrue(bookDBAssignId != -1);
        Log.e("testInsertData", "pass" + bookDBAssignId);

    }


    public void testIsDataCorrectInDB(){
        MaBaseSQLite maBaseSQLite = new MaBaseSQLite(mContext);
        SQLiteDatabase db = maBaseSQLite.getWritableDatabase();
        Cursor cursor = db.query(MaBaseSQLite.getTableLivre(), null, null, null, null, null, null);
        assertTrue(cursor.moveToFirst());

        int idColumnIndex = cursor.getColumnIndex(MaBaseSQLite.getColonneId());
        int dbId = cursor.getInt(idColumnIndex);

        int bookTitleColumnIndex = cursor.getColumnIndex(MaBaseSQLite.getCOLONNE_Titre());
        String dbBookTitle = cursor.getString(bookTitleColumnIndex);

        int bookAuthorColumnIndex = cursor.getColumnIndex(MaBaseSQLite.getColonneNom());
        String dbBookAuthor = cursor.getString(bookAuthorColumnIndex);

        int bookYearColumIndex = cursor.getColumnIndex(MaBaseSQLite.getColonneAnnee());
        int dbBookYear = cursor.getInt(bookYearColumIndex);

        assertEquals(bookDBAssignId, dbId);
        assertEquals(bookTitle, dbBookTitle);
        assertEquals(bookAuthor, dbBookAuthor);
        assertEquals(bookYear, dbBookYear);
        Log.e("testIsDataCorrectInDB", "pass");

    }
}
