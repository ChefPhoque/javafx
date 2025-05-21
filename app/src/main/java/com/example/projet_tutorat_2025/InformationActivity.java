package com.example.projet_tutorat_2025;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InformationActivity extends AppCompatActivity {



private String nomEtu;
    private TextView nomEtudiant, prenomEtudiant, emailEtudiant, telEtudiant;
    private TextView nomMaitre, prenomMaitre, telMaitre;
    private TextView nomEntreprise, adresseEntreprise;

    private Button retour;

    private int idEtudiantConnecte;


    EtudiantDataSource etudiantBdd = new EtudiantDataSource(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        initialisation();
    }

    private void initialisation() {
        etudiantBdd.open();
idEtudiantConnecte = getIntent().getIntExtra("idEtudiant",-1);

        retour = findViewById(R.id.back_button);
        nomEtudiant = findViewById(R.id.tv_nom);
        prenomEtudiant = findViewById(R.id.tv_prenom);
        emailEtudiant = findViewById(R.id.tv_email);
        telEtudiant = findViewById(R.id.tv_telephone);

        nomMaitre = findViewById(R.id.tv_nom_maitre);
        prenomMaitre = findViewById(R.id.tv_prenom_maitre);
        telMaitre = findViewById(R.id.tv_tel_maitre);

        nomEntreprise = findViewById(R.id.tv_nom_entreprise);
        adresseEntreprise = findViewById(R.id.tv_adresse_entreprise);


        Etudiant etudiant = etudiantBdd.getEtudiantById(idEtudiantConnecte);


        if (etudiant == null) {

            nomEtudiant.setText("Aucune donnée à afficher");
            prenomEtudiant.setText("Aucune donnée à afficher");
            emailEtudiant.setText("Aucune donnée à afficher");
            telEtudiant.setText("Aucune donnée à afficher");

            nomMaitre.setText("Aucune donnée à afficher");
            prenomMaitre.setText("Aucune donnée à afficher");
            telMaitre.setText("Aucune donnée à afficher");

            nomEntreprise.setText("Aucune donnée à afficher");
            adresseEntreprise.setText("Aucune donnée à afficher");
        } else {


            nomEtudiant.setText("Nom: " + etudiant.getNom());
            prenomEtudiant.setText("Prénom: " + etudiant.getPrenom());
            emailEtudiant.setText("Email: " + etudiant.getMail());
            telEtudiant.setText("Téléphone: " + etudiant.getTel());

            nomMaitre.setText("Nom: " + etudiant.getNomMaitre());
            prenomMaitre.setText("Prénom: " + etudiant.getPrenomMaitre());
            telMaitre.setText("Téléphone: " + etudiant.getTelMaitre());

            nomEntreprise.setText("Nom: " + etudiant.getNomEntreprise());
            adresseEntreprise.setText("Adresse: " + etudiant.getAdresseEntreprise() + ", \n " + etudiant.getCodePostalEntreprise() + ", " + etudiant.getVilleEntreprise() );
        }

        nomEtu = etudiant.getNom();
        etudiantBdd.close();

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InformationActivity.this, AccueilActivity.class);
                intent.putExtra("idEtudiant",idEtudiantConnecte);
                intent.putExtra("nomEtu", nomEtu);

                startActivity(intent);
            }
        });

    }




}
