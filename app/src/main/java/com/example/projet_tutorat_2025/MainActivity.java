package com.example.projet_tutorat_2025;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button confirmer;
    private EditText login;
    private EditText password;

    EtudiantDataSource dataSource;
    BilanDataSource dataSourceBilan;
    Bilan2DataSource dataSourceBilan2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dataSource = new EtudiantDataSource(this);
        dataSourceBilan = new BilanDataSource(this);
        dataSourceBilan2 = new Bilan2DataSource(this);

        dataSource.open();
        initialisation();
    }
    private void resetDatabase() {
        this.deleteDatabase("projet_tutorat.db"); // Remplace par le vrai nom de ta BDD
        Log.d("BDD", "Base de données supprimée !");

        // Réouverture des DataSources pour recréer la BDD
        dataSource = new EtudiantDataSource(this);
        dataSourceBilan = new BilanDataSource(this);
        dataSourceBilan2 = new Bilan2DataSource(this);

        dataSource.open();
        dataSourceBilan.open();
        dataSourceBilan2.open();

        Log.d("BDD", "Base de données recréée !");
    }

    public void initialisation() {
        login = findViewById(R.id.username);
        password = findViewById(R.id.password);
        confirmer = findViewById(R.id.login_button);

        confirmer.setOnClickListener(v -> {
            String identifiant = login.getText().toString();
            String motdepasse = password.getText().toString();

            if (!identifiant.isEmpty() && !motdepasse.isEmpty()) {
                verifyLoginPasswordWithAPI(identifiant, motdepasse);
            } else {
                Toast.makeText(MainActivity.this, "Veuillez entrer un identifiant et un mot de passe.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void verifyLoginPasswordWithAPI(String login, String password) {
        RetroFitClientEtudiant.getInstance().getMyApi().verifyLoginPassword(login, password).enqueue(new Callback<Etudiant>() {
            @Override
            public void onResponse(Call<Etudiant> call, Response<Etudiant> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Etudiant etudiant = response.body();
                    int idE = etudiant.getId();

                    if (dataSource.isEtudiantExists(idE)) {
                        dataSource.updateEtudiant(etudiant);
                    } else {
                        dataSource.insertEtudiant(etudiant);
                    }

                    fetchBilans(idE, login, password);

                    Intent intent = new Intent(MainActivity.this, AccueilActivity.class);
                    intent.putExtra("idEtudiant", idE);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Identifiant ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Etudiant> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Erreur de connexion avec le serveur", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchBilans(int etudiantId, String login, String password) {
        RetroFitClientEtudiant.getInstance().getMyApi().verifyLoginPassword1(login, password).enqueue(new Callback<Bilan1>() {
            @Override
            public void onResponse(Call<Bilan1> call, Response<Bilan1> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Bilan1 bilan1 = response.body();
                    bilan1.setMoyenne((bilan1.getNoteBil() + bilan1.getNoteOral1() + bilan1.getNoteDossier1()) / 3);
dataSourceBilan.open();
                    if (dataSourceBilan.isBilan1Exists(bilan1.getIdEtu())) {
                        dataSourceBilan.updateBilan1(bilan1);
                    } else {
                        dataSourceBilan.insertBilan(bilan1);
                    }
                }
            }

            @Override
            public void onFailure(Call<Bilan1> call, Throwable t) {
                Log.e("API", "Échec de récupération Bilan1: " + t.getMessage());
            }
        });

        RetroFitClientEtudiant.getInstance().getMyApi().verifyLoginPassword3(login, password).enqueue(new Callback<Bilan2>() {
            @Override
            public void onResponse(Call<Bilan2> call, Response<Bilan2> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Bilan2 bilan2 = response.body();
                    bilan2.setMoyenne((bilan2.getNoteOral() + bilan2.getNoteDossier()) / 2);
dataSourceBilan2.open();
                    if (dataSourceBilan2.isBilan2Existe(bilan2.getIdEtudiant())) {
                        dataSourceBilan2.updateBilan2(bilan2);
                    } else {
                        dataSourceBilan2.insertBilan(bilan2);
                    }
                }
            }

            @Override
            public void onFailure(Call<Bilan2> call, Throwable t) {
                Log.e("API", "Échec de récupération Bilan2: " + t.getMessage());
            }
        });
    }
}
