package com.example.projet_tutorat_2025;

import static com.example.projet_tutorat_2025.DatabaseHelper.TABLE_BILAN1;
import static com.example.projet_tutorat_2025.DatabaseHelper.TABLE_BILAN2;
import static com.example.projet_tutorat_2025.DatabaseHelper.TABLE_ETUDIANT;
import static com.example.projet_tutorat_2025.DatabaseHelper.TABLE_BILAN1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class EtudiantDataSource {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public EtudiantDataSource(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Etudiant insertEtudiant(Etudiant etudiant) {
        ContentValues values = new ContentValues();
        values.put("id", etudiant.getId());
        values.put("nom", etudiant.getNom());
        values.put("prenom", etudiant.getPrenom());
        values.put("mail", etudiant.getMail());
        values.put("tel", etudiant.getTel());
        values.put("adresse", etudiant.getAdresse());
        values.put("code_postal", etudiant.getCodePostal());
        values.put("ville", etudiant.getVille());
        values.put("login", etudiant.getLogin());
        values.put("nom_maitre", etudiant.getNomMaitre());
        values.put("prenom_maitre", etudiant.getPrenomMaitre());
        values.put("tel_maitre", etudiant.getTelMaitre());
        values.put("nom_entreprise", etudiant.getNomEntreprise());
        values.put("adresse_entreprise", etudiant.getAdresseEntreprise());
        values.put("code_postal_entreprise", etudiant.getCodePostalEntreprise());
        values.put("ville_entreprise", etudiant.getVilleEntreprise());

        int id = (int) database.insert(TABLE_ETUDIANT, null, values);
        etudiant.setId(id);

        return etudiant;
    }
    public boolean isEtudiantExists(int idEtudiant) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ETUDIANT + " WHERE id = ?", new String[]{String.valueOf(idEtudiant)});
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }
    public boolean isBilan1Exists(int idEtudiant) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_BILAN1 + " WHERE id_etudiant = ?", new String[]{String.valueOf(idEtudiant)});
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }
    public boolean isBilan2Exists(int idEtudiant) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_BILAN2 + " WHERE id_etudiant = ?", new String[]{String.valueOf(idEtudiant)});
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }
    public void updateEtudiant(Etudiant etudiant) {
        ContentValues values = new ContentValues();
        values.put("nom", etudiant.getNom());
        values.put("prenom", etudiant.getPrenom());
        values.put("mail", etudiant.getMail());
        values.put("tel", etudiant.getTel());
        values.put("adresse", etudiant.getAdresse());
        values.put("code_postal", etudiant.getCodePostal());
        values.put("ville", etudiant.getVille());
        values.put("login", etudiant.getLogin());
        values.put("nom_maitre", etudiant.getNomMaitre());
        values.put("prenom_maitre", etudiant.getPrenomMaitre());
        values.put("tel_maitre", etudiant.getTelMaitre());
        values.put("nom_entreprise", etudiant.getNomEntreprise());
        values.put("adresse_entreprise", etudiant.getAdresseEntreprise());
        values.put("code_postal_entreprise", etudiant.getCodePostalEntreprise());
        values.put("ville_entreprise", etudiant.getVilleEntreprise());

        int rowsUpdated = database.update(TABLE_ETUDIANT, values, "id = ?",
                new String[]{String.valueOf(etudiant.getId())});

        if (rowsUpdated > 0) {
            Log.d("DEBUG_BDD", "Étudiant mis à jour avec ID: " + etudiant.getId());
        } else {
            Log.e("DEBUG_BDD", "Échec de la mise à jour pour l'étudiant ID: " + etudiant.getId());
        }
    }

    public Bilan1 insertBilan1(Bilan1 bilan1) {
        ContentValues values = new ContentValues();
        values.put("noteBil", bilan1.getNoteBil());
        values.put("noteOral1", bilan1.getNoteOral1());
        values.put("noteDossier1", bilan1.getNoteDossier1());
        values.put("moyenne", bilan1.getMoyenne());
        values.put("remarque", bilan1.getRemarque1());
        values.put("id_etudiant", bilan1.getIdEtu());

        int id = (int) database.insert(TABLE_BILAN1, null, values);
        bilan1.setId(id);

        return bilan1;
    }
    private Etudiant cursorToEtudiant(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
        String nom = cursor.getString(cursor.getColumnIndexOrThrow("nom"));
        String prenom = cursor.getString(cursor.getColumnIndexOrThrow("prenom"));
        String mail = cursor.getString(cursor.getColumnIndexOrThrow("mail"));
        String tel = cursor.getString(cursor.getColumnIndexOrThrow("tel"));
        String adresse = cursor.getString(cursor.getColumnIndexOrThrow("adresse"));
        String codePostal = cursor.getString(cursor.getColumnIndexOrThrow("code_postal"));
        String ville = cursor.getString(cursor.getColumnIndexOrThrow("ville"));
        String login = cursor.getString(cursor.getColumnIndexOrThrow("login"));
        String nomMaitre = cursor.getString(cursor.getColumnIndexOrThrow("nom_maitre"));
        String prenomMaitre = cursor.getString(cursor.getColumnIndexOrThrow("prenom_maitre"));
        String telMaitre = cursor.getString(cursor.getColumnIndexOrThrow("tel_maitre"));
        String nomEntreprise = cursor.getString(cursor.getColumnIndexOrThrow("nom_entreprise"));
        String adresseEntreprise = cursor.getString(cursor.getColumnIndexOrThrow("adresse_entreprise"));
        String codePostalEntreprise = cursor.getString(cursor.getColumnIndexOrThrow("code_postal_entreprise"));
        String villeEntreprise = cursor.getString(cursor.getColumnIndexOrThrow("ville_entreprise"));

        return new Etudiant(id, nom, prenom, mail, tel, adresse, codePostal, ville, login, nomMaitre, prenomMaitre, telMaitre, nomEntreprise, adresseEntreprise, codePostalEntreprise, villeEntreprise);
    }
    private Bilan1 cursorToBilan1(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
        float noteBil = cursor.getFloat(cursor.getColumnIndexOrThrow("noteBil"));
        int noteOral1 = cursor.getInt(cursor.getColumnIndexOrThrow("noteOral1"));
        float noteDossier1 = cursor.getFloat(cursor.getColumnIndexOrThrow("noteDossier1"));
        float moyenne = cursor.getFloat(cursor.getColumnIndexOrThrow("moyenne"));
        String remarque1 = cursor.getString(cursor.getColumnIndexOrThrow("remarque1"));
        int idEtu = cursor.getInt(cursor.getColumnIndexOrThrow("idEtu"));

        return new Bilan1(id, noteBil, noteOral1, noteDossier1, moyenne, remarque1, idEtu);
    }

    public ArrayList<Etudiant> getAllEtudiants() {
        ArrayList<Etudiant> etudiants = new ArrayList<>();
        Cursor cursor = database.query(true, TABLE_ETUDIANT, new String[]{
                        "id", "nom", "prenom", "mail", "tel", "adresse", "code_postal", "ville", "login", "nom_maitre", "prenom_maitre", "tel_maitre", "nom_entreprise", "adresse_entreprise", "code_postal_entreprise", "ville_entreprise"},
                null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            Etudiant etudiant = cursorToEtudiant(cursor);
            etudiants.add(etudiant);
        }
        cursor.close();
        return etudiants;
    }

    public Etudiant getEtudiantById(int id) {
        Etudiant etudiant = null;
        Cursor cursor = database.query(true, TABLE_ETUDIANT, new String[]{
                        "id", "nom", "prenom", "mail", "tel", "adresse", "code_postal", "ville", "login", "nom_maitre", "prenom_maitre", "tel_maitre",  "nom_entreprise",  "adresse_entreprise", "code_postal_entreprise", "ville_entreprise"},
                "id = ?", new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor.moveToFirst()) {
            etudiant = cursorToEtudiant(cursor);
        }
        cursor.close();
        return etudiant;
    }

    public void deleteEtudiant(Etudiant etudiant) {
        int id = etudiant.getId();
        database.delete(TABLE_ETUDIANT, "id = ?", new String[]{String.valueOf(id)});
    }
}
