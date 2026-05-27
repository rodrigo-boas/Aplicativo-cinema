package com.example.a04.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FilmeApi {
    @GET("search/movie")
    Call<Results> buscarFilme(
            @Query("api_key")
            String chaveApi,
            @Query("language")
            String idioma,
            @Query("query")
            String nomeFilme
    );

    @GET("discover/movie")
    Call<Results> descobrirFilmes(
            @Query("api_key")
            String chaveApi,
            @Query("language")
            String idioma
    );

    @GET("discover/movie")
    Call<Results> buscarPorGenero(
            @Query("api_key")
            String chaveApi,
            @Query("language")
            String idioma,
            @Query("with_genres")
            String generos,
            @Query("page")
            int pagina
    );

    @GET("movie/{movie_id}")
    Call<Filme> buscarPorId(
            @Path("movie_id")
            int id,
            @Query("api_key")
            String chaveApi,
            @Query("language")
            String idioma
    );
}
