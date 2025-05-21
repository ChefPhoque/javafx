package com.example.projet_tutorat_2025;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

public class Bilan2DataSource {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public Bilan2DataSource(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }
    public boolean isBilan2Existe(int idEtudiant) {
        Cursor cursor = database.query(DatabaseHelper.TABLE_BILAN2, new String[]{"id"},
                "id_etudiant = ?", new String[]{String.valueOf(idEtudiant)}, null, null, null);

        boolean existe = cursor.getCount() > 0;
        cursor.close();

        return existe;
    }

    public void updateBilan2(Bilan2 bilan2) {
        ContentValues values = new ContentValues();
        values.put("note_oral", bilan2.getNoteOral());
        values.put("note_dossier", bilan2.getNoteDossier());
        values.put("moyenne", bilan2.getMoyenne());
        values.put("remarque", bilan2.getRemarque());
        values.put("sujet_memoire", bilan2.getSujetMemoire());

        int rowsUpdated = database.update(DatabaseHelper.TABLE_BILAN2, values, "id_etudiant = ?",
                new String[]{String.valueOf(bilan2.getIdEtudiant())});

        if (rowsUpdated > 0) {
            Log.d("DEBUG_BDD", "Bilan2 mis à jour pour l'étudiant ID: " + bilan2.getIdEtudiant());
        } else {
            Log.e("DEBUG_BDD", "Échec de la mise à jour pour l'étudiant ID: " + bilan2.getIdEtudiant());
        }
    }

    public Bilan2 insertBilan(Bilan2 bilan2) {
        ContentValues values = new ContentValues();
        values.put("note_oral", bilan2.getNoteOral());
        values.put("note_dossier", bilan2.getNoteDossier());
        values.put("moyenne", bilan2.getMoyenne());
        values.put("remarque", bilan2.getRemarque());
        values.put("sujet_memoire", bilan2.getSujetMemoire());
        values.put("id_etudiant", bilan2.getIdEtudiant());

        int id = (int) database.insert(DatabaseHelper.TABLE_BILAN2, null, values);
        bilan2.setId(id);

        return bilan2;
    }

    private Bilan2 cursorToBilan(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
        // Récupérer la valeur du timestamp à partir du cursor




        float noteOral = cursor.getFloat(cursor.getColumnIndexOrThrow("note_oral"));
        float noteDossier = cursor.getFloat(cursor.getColumnIndexOrThrow("note_dossier"));
        float moyenne = cursor.getFloat(cursor.getColumnIndexOrThrow("moyenne"));
        String remarque = cursor.getString(cursor.getColumnIndexOrThrow("remarque"));
        String sujetMemoire = cursor.getString(cursor.getColumnIndexOrThrow("sujet_memoire"));
        int idEtudiant = cursor.getInt(cursor.getColumnIndexOrThrow("id_etudiant"));

        return new Bilan2(id, noteOral, noteDossier, moyenne, remarque, sujetMemoire, idEtudiant);
    }

    public ArrayList<Bilan2> getAllBilans() {
        ArrayList<Bilan2> bilans = new ArrayList<>();
        Cursor cursor = database.query(true, DatabaseHelper.TABLE_BILAN2, new String[]{
                        "id", "note_oral", "note_dossier", "moyenne", "remarque", "sujet_memoire", "id_etudiant"},
                null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            Bilan2 bilan = cursorToBilan(cursor);
            bilans.add(bilan);
        }
        cursor.close();
        return bilans;
    }

    public Bilan2 getBilanByEtudiantId(int idEtudiant) {
        Bilan2 bilan = null;
        Cursor cursor = database.query(true, DatabaseHelper.TABLE_BILAN2, new String[]{
                        "id","note_oral", "note_dossier", "moyenne", "remarque", "sujet_memoire", "id_etudiant"},
                "id_etudiant = ?", new String[]{String.valueOf(idEtudiant)}, null, null, null, null);

        if (cursor.moveToFirst()) {
            bilan = cursorToBilan(cursor);
        }
        cursor.close();
        return bilan;
    }

    public void deleteBilan(Bilan2 bilan) {
        int id = bilan.getId();
        database.delete(DatabaseHelper.TABLE_BILAN2, "id = ?", new String[]{String.valueOf(id)});
    }
}
