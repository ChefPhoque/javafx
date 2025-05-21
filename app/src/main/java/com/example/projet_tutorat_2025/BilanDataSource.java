package com.example.projet_tutorat_2025;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class BilanDataSource {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public BilanDataSource(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }
    public boolean isBilan1Exists(int id) {
        Cursor cursor = database.rawQuery("SELECT 1 FROM " + DatabaseHelper.TABLE_BILAN1 + " WHERE id = ?", new String[]{String.valueOf(id)});
        boolean exists = cursor.moveToFirst();
        cursor.close();
        return exists;
    }
    public void updateBilan1(Bilan1 bilan1) {
        ContentValues values = new ContentValues();
        values.put("note_bilan", bilan1.getNoteBil());
        values.put("note_oral", bilan1.getNoteOral1());
        values.put("note_dossier", bilan1.getNoteDossier1());
        values.put("moyenne", bilan1.getMoyenne());
        values.put("remarque", bilan1.getRemarque1());

        int rowsUpdated = database.update(DatabaseHelper.TABLE_BILAN1, values, "id = ?", new String[]{String.valueOf(bilan1.getId())});

        if (rowsUpdated > 0) {
            Log.d("DEBUG_BDD", "Bilan1 mis à jour avec ID: " + bilan1.getId());
        } else {
            Log.e("DEBUG_BDD", "Échec de la mise à jour pour Bilan1 ID: " + bilan1.getId());
        }
    }


    public Bilan1 insertBilan(Bilan1 bilan1) {
        ContentValues values = new ContentValues();
        values.put("note_bilan", bilan1.getNoteBil());
        values.put("note_oral", bilan1.getNoteOral1());
        values.put("note_dossier", bilan1.getNoteDossier1());
        values.put("moyenne", bilan1.getMoyenne());
        values.put("remarque", bilan1.getRemarque1());
        values.put("id_etudiant", bilan1.getIdEtu());

        int id = (int) database.insert(DatabaseHelper.TABLE_BILAN1, null, values);
        bilan1.setId(id);
        if (id == -1) {
            Log.e("DEBUG_BDD", "Insertion échouée pour Bilan1 !");
        } else {
            Log.d("DEBUG_BDD", "Bilan1 inséré avec ID: " + id);
        }

        return bilan1;
    }

    private Bilan1 cursorToBilan(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
        float noteBilan = cursor.getFloat(cursor.getColumnIndexOrThrow("note_bilan"));
        float noteOral = cursor.getFloat(cursor.getColumnIndexOrThrow("note_oral"));
        float noteDossier = cursor.getFloat(cursor.getColumnIndexOrThrow("note_dossier"));
        float moyenne = cursor.getFloat(cursor.getColumnIndexOrThrow("moyenne"));
        String remarque = cursor.getString(cursor.getColumnIndexOrThrow("remarque"));
        int idEtudiant = cursor.getInt(cursor.getColumnIndexOrThrow("id_etudiant"));

        return new Bilan1(id, noteBilan, noteOral, noteDossier, moyenne, remarque, idEtudiant);
    }

    public ArrayList<Bilan1> getAllBilans() {
        ArrayList<Bilan1> bilans = new ArrayList<>();
        Cursor cursor = database.query(true, DatabaseHelper.TABLE_BILAN1, new String[]{
                        "id", "note_bilan", "note_oral", "note_dossier", "moyenne", "remarque", "id_etudiant"},
                null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            Bilan1 bilan = cursorToBilan(cursor);
            bilans.add(bilan);
        }
        cursor.close();
        return bilans;
    }

    public Bilan1 getBilanByEtudiantId(int idEtudiant) {
        Bilan1 bilan = null;
        Cursor cursor = database.query(true, DatabaseHelper.TABLE_BILAN1, new String[]{
                        "id", "note_bilan", "note_oral", "note_dossier", "moyenne", "remarque", "id_etudiant"},
                "id_etudiant = ?", new String[]{String.valueOf(idEtudiant)}, null, null, null, null);

        if (cursor.moveToFirst()) {
            bilan = cursorToBilan(cursor);
        }
        cursor.close();
        return bilan;
    }

    public void deleteBilan(Bilan1 bilan) {
        int id = bilan.getId();
        database.delete(DatabaseHelper.TABLE_BILAN1, "id = ?", new String[]{String.valueOf(id)});
    }
}
