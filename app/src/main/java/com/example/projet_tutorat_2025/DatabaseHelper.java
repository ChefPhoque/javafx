package com.example.projet_tutorat_2025;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_ETUDIANT = "Etudiant";
    public static final String TABLE_BILAN1 = "Bilan1";
    public static final String TABLE_BILAN2 = "Bilan2";

    private static final String DATABASE_NAME = "FSI.db";
    private static final int DATABASE_VERSION = 1;


    private static final String DATABASE_CREATE_ETUDIANT = "CREATE TABLE IF NOT EXISTS "
            + TABLE_ETUDIANT + "(id INTEGER PRIMARY KEY , "
            + "nom TEXT NOT NULL, "
            + "prenom TEXT NOT NULL, "
            + "mail TEXT, "
            + "tel TEXT, "
            + "adresse TEXT, "
            + "code_postal TEXT, "
            + "ville TEXT, "
            + "login TEXT, "
            + "nom_maitre TEXT, "
            + "prenom_maitre TEXT, "
            + "tel_maitre TEXT, "
            + "nom_entreprise TEXT, "
            + "adresse_entreprise TEXT, "
            + "code_postal_entreprise TEXT, "
            + "ville_entreprise TEXT);";


    private static final String DATABASE_CREATE_BILAN1 = "CREATE TABLE IF NOT EXISTS "
            + TABLE_BILAN1 + "(id INTEGER PRIMARY KEY , "
            + "note_bilan REAL, "
            + "note_oral REAL, "
            + "note_dossier REAL, "
            + "moyenne REAL, "
            + "remarque TEXT, "
            + "id_etudiant INTEGER, "
            + "FOREIGN KEY(id_etudiant) REFERENCES " + TABLE_ETUDIANT + "(id) ON DELETE CASCADE);";


    private static final String DATABASE_CREATE_BILAN2 = "CREATE TABLE IF NOT EXISTS "
            + TABLE_BILAN2 + "(id INTEGER PRIMARY KEY , "
            + "note_oral REAL, "
            + "note_dossier REAL, "
            + "moyenne REAL, "
            + "remarque TEXT, "
            + "sujet_memoire TEXT, "
            + "id_etudiant INTEGER, "
            + "FOREIGN KEY(id_etudiant) REFERENCES " + TABLE_ETUDIANT + "(id) ON DELETE CASCADE);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void clearAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_BILAN1);
        db.execSQL("DELETE FROM " + TABLE_BILAN2);
        db.execSQL("DELETE FROM " + TABLE_ETUDIANT);
        Log.d("BDD", "Toutes les données ont été supprimées !");
    }


    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE_ETUDIANT);
        database.execSQL(DATABASE_CREATE_BILAN1);
        database.execSQL(DATABASE_CREATE_BILAN2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DatabaseHelper.class.getName(),
                "Mise à niveau de la base de données de la version " + oldVersion + " à "
                        + newVersion + ", ce qui supprimera toutes les anciennes données");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BILAN1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BILAN2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ETUDIANT);
        onCreate(db);
    }
}


