package com.example.projet_tutorat_2025;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Query;
import retrofit2.http.Field;

public interface API {
    String BASE_URL = "https://projetfsi.alwaysdata.net/vue/";




    // Méthode pour vérifier le login et le mot de passe
    @FormUrlEncoded
    @POST("PageAPI.php")
    Call<Etudiant> verifyLoginPassword(@Field("login") String login, @Field("password") String password);
    @FormUrlEncoded
    @POST("PageAPI.php")
    Call<Bilan1> verifyLoginPassword1(@Field("login") String login, @Field("password") String password);

    @FormUrlEncoded
    @POST("PageAPI.php")
    Call<Bilan2> verifyLoginPassword3(@Field("login") String login, @Field("password") String password);

}
