package com.example.projet_tutorat_2025;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AccueilActivity extends AppCompatActivity {
    EtudiantDataSource dataSource;
    private String nomEtu;
    private int idE;
    private Button info;
    private Button notes;
    private Button logout;
    private TextView affichage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        nomEtu = getIntent().getStringExtra("nomEtu");
        idE = getIntent().getIntExtra("idEtudiant", -1);

        Log.d("AccueilActivity", "Nom de l'utilisateur : " + nomEtu);

        dataSource = new EtudiantDataSource(this);
        dataSource.open();

        initialisation();
    }

    public void initialisation() {
        affichage = findViewById(R.id.student_name);
        affichage.setText("Bienvenue, " + nomEtu);

        info = findViewById(R.id.btn_profile);
        notes = findViewById(R.id.btn_notes);
        logout = findViewById(R.id.btn_logout); // Ajout du bouton logout

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent information = new Intent(AccueilActivity.this, InformationActivity.class);
                information.putExtra("idEtudiant", idE);
                startActivity(information);
            }
        });

        notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent notes = new Intent(AccueilActivity.this, NotesActivity.class);
                notes.putExtra("idEtudiant", idE);
                startActivity(notes);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccueilActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Supprime l'historique
                startActivity(intent);
                finish(); // Ferme AccueilActivity
            }
        });
    }
}
