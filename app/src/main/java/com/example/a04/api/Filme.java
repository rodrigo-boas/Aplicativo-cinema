package com.example.a04.api;

import android.widget.ImageView;

import com.google.gson.annotations.SerializedName;
import com.google.type.DateTime;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Filme {

    @SerializedName("id")
    private int filme_id;

    @SerializedName("title")
    private String titulo;

    @SerializedName("overview")
    private String descricao;

    @SerializedName("poster_path")
    private String poster;

    @SerializedName("genre_ids")
    private ArrayList<Integer> generos_id;

    @SerializedName("release_date")
    private Date data_lancamento;

    @SerializedName("vote_average")
    private double avaliacao;

    public int getFilme_id() {
        return filme_id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getPoster() {
        return poster;
    }

    public ArrayList<Integer> getGeneros_id() {
        return generos_id;
    }

    public Date getData_lancamento() {
        return data_lancamento;
    }

    public double getAvaliacao() {
        return avaliacao;
    }
}
