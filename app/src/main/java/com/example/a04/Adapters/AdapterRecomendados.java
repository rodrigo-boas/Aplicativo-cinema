package com.example.a04.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a04.R;
import com.example.a04.api.Filme;

import java.util.ArrayList;

public class AdapterRecomendados extends RecyclerView.Adapter<AdapterRecomendados.ViewHolder> {

    private ArrayList<Filme> listaFilmes;
    private Context context;

    public AdapterRecomendados(ArrayList<Filme> listaFilmes, Context context) {
        this.listaFilmes = listaFilmes;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterRecomendados.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.filme_grid, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecomendados.ViewHolder holder, int position) {
        Filme filme = listaFilmes.get(position);
        holder.text_rating.setText(String.format("%.1f", filme.getAvaliacao()));
        holder.text_title.setText(filme.getTitulo());

        String image_url = "https://image.tmdb.org/t/p/w500" + filme.getPoster();
        Glide.with(context).load(image_url).into(holder.image_poster);
    }

    @Override
    public int getItemCount() {
        return listaFilmes.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image_poster;
        TextView text_title;
        TextView text_rating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image_poster = itemView.findViewById(R.id.image_poster);
            text_title = itemView.findViewById(R.id.text_title);
            text_rating = itemView.findViewById(R.id.text_rating);
        }
    }
}
