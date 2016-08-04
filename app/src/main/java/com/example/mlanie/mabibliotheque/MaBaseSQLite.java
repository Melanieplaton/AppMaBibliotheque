package com.example.mlanie.mabibliotheque;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mélanie on 04/08/2016.
 */
public class MaBaseSQLite extends SQLiteOpenHelper {

    private static final String TABLE_LIVRE = "table_livre";
    private static final String COLONNE_ID = "ID";
    private static final String COLONNE_Titre = "Titre";
    private static final String COLONNE_NOM = "Nom_Auteur";
    private static final String COLONNE_ANNEE = "Annee_Publication";

    private static final String CREATE_BDD = "CREATE TABLE"+ TABLE_LIVRE+"("+COLONNE_ID+"INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLONNE_Titre+"TEXT NOT NULL,"+COLONNE_NOM+"TEXT NOT NULL,"+ COLONNE_ANNEE+"INT);";

    public MaBaseSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //On créé la table à partir de la requête écrite dans la variable CREATE_BDD
        sqLiteDatabase.execSQL(CREATE_BDD);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(CREATE_BDD);
        onCreate(sqLiteDatabase);

    }
}
