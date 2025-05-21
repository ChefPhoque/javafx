package com.example.projet_tutorat_2025;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class NotesActivity extends AppCompatActivity {

    private TextView noteOralBilan1, noteDossierBilan1, moyenneBilan1, remarqueBilan1, noteBilan1;
    private TextView noteOralBilan2, remarqueBilan2,noteDossierBilan2, moyenneBilan2, sujMem;
    private Button retour;

    BilanDataSource bilan1 = new BilanDataSource(this);
    Bilan2DataSource bilan2 = new Bilan2DataSource(this);
    EtudiantDataSource etudiantbdd = new EtudiantDataSource(this);

    private int idEtudiant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        idEtudiant = getIntent().getIntExtra("idEtudiant", -1);
        initialisation();
    }

    private void initialisation() {
        bilan1.open();
        bilan2.open();
        //Bilan 1

        noteOralBilan1 = findViewById(R.id.noteoral1);
        noteDossierBilan1 = findViewById(R.id.noteDossier1);
        moyenneBilan1 = findViewById(R.id.Moyenne1);
        remarqueBilan1 = findViewById(R.id.remarque1);
        noteBilan1 = findViewById(R.id.noteBilan);

        //Bilan 2

        noteOralBilan2 = findViewById(R.id.noteoral2);
        remarqueBilan2 = findViewById(R.id.Remarque2);
        noteDossierBilan2 = findViewById(R.id.noteDossier2);
        moyenneBilan2 = findViewById(R.id.moyenneBilan2);
        sujMem = findViewById(R.id.sujMem);

        retour = findViewById(R.id.back_button);

        Bilan1 bilanE1 = bilan1.getBilanByEtudiantId(idEtudiant);
        Bilan2 bilanE2 = bilan2.getBilanByEtudiantId(idEtudiant);
        etudiantbdd.open();
        Etudiant etudiant = etudiantbdd.getEtudiantById(idEtudiant);
        String nomEtu = etudiant.getNom();


if(bilanE1 == null && bilan2 == null){
    noteOralBilan1.setText("Aucune donnée à afficher");
    noteDossierBilan1.setText("Aucune donnée à afficher");
    moyenneBilan1.setText("Aucune donnée à afficher");
    remarqueBilan1.setText("Aucune donnée à afficher");
    noteBilan1.setText("Aucune donnée à afficher");

    // Informations sur le Bilan 2
    noteOralBilan2.setText("Aucune donnée à afficher");
    remarqueBilan2.setText("Aucune donnée à afficher");
    noteDossierBilan2.setText("Aucune donnée à afficher");
    moyenneBilan2.setText("Aucune donnée à afficher");
    sujMem.setText("Aucune donnée à afficher");
}else{
    noteOralBilan1.setText(String.valueOf(bilanE1.getNoteOral1()));
    noteDossierBilan1.setText(String.valueOf(bilanE1.getNoteDossier1()));
    moyenneBilan1.setText(String.format(Locale.getDefault(), "%.2f", bilanE1.getMoyenne()));
    //Locale.getDafault permet d'utiliser le format numérique du téléphone
    // "%.2f" permet de prendre uniquement deux chiffre après la virgule

    remarqueBilan1.setText(bilanE1.getRemarque1());
    noteBilan1.setText(String.valueOf(bilanE1.getNoteBil()));

    // Informations sur le Bilan 2
    noteOralBilan2.setText(String.valueOf(bilanE2.getNoteOral()));
    remarqueBilan2.setText(bilanE2.getRemarque());
    noteDossierBilan2.setText(String.valueOf(bilanE2.getNoteDossier()));
    moyenneBilan2.setText(String.format(Locale.getDefault(), "%.2f", bilanE2.getMoyenne()));

    sujMem.setText( bilanE2.getSujetMemoire());
}





        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotesActivity.this, AccueilActivity.class);
                intent.putExtra("idEtudiant",idEtudiant);
                intent.putExtra("nomEtu", nomEtu);

                startActivity(intent);
            }
        });
    }

}
