package com.example.projet_tutorat_2025;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitClientEtudiant {
    private static RetroFitClientEtudiant instance = null;
    private API myApi;

    private RetroFitClientEtudiant() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL) // Pas de PageAPI.php ici
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApi = retrofit.create(API.class);
    }

    public static RetroFitClientEtudiant getInstance() {
        if (instance == null) {
            instance = new RetroFitClientEtudiant();
        }
        return instance;
    }

    public API getMyApi() {
        return myApi;
    }
}
