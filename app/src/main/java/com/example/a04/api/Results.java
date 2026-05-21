package com.example.a04.api;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Results {
    @SerializedName("results")
    private ArrayList<Filme> resultados;

    public ArrayList<Filme> getResultados() {
        return resultados;
    }
}
