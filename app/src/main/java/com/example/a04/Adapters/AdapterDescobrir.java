package com.example.a04.Adapters;

import static android.view.View.INVISIBLE;

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

import java.util.List;

public class AdapterDescobrir extends RecyclerView.Adapter<AdapterDescobrir.FilmeViewHolder> {

    private List<Filme> listaFilmes;
    private Context context;

    public AdapterDescobrir(List<Filme> listaFilmes, Context context) {
        this.listaFilmes = listaFilmes;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterDescobrir.FilmeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.filme_card_descobrir, parent, false);
        return new FilmeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDescobrir.FilmeViewHolder holder, int position) {
        Filme filme = listaFilmes.get(position);
        holder.textTitulo.setText(filme.getTitulo());
        holder.textNota.setText(String.format("%.1f", filme.getAvaliacao()));
        holder.text_year.setText(filme.getAnoLancamento());
        holder.text_overview.setText(filme.getDescricao());
        holder.text_genre1.setText(Filme.getGeneros(filme.getGeneros_id().get(0)));

        if(filme.getGeneros_id().size() > 1) {
            holder.text_genre2.setText(Filme.getGeneros(filme.getGeneros_id().get(1)));
        } else {
            holder.text_genre2.setVisibility(INVISIBLE);
        }

        String image_url = "https://image.tmdb.org/t/p/w500" + filme.getPoster();

        Glide.with(context).load(image_url).into(holder.imagePoster);
    }

    @Override
    public int getItemCount() {
        return listaFilmes.size();
    }

    class FilmeViewHolder extends RecyclerView.ViewHolder {
        ImageView imagePoster;
        TextView textTitulo;
        TextView textNota;
        TextView text_year;
        TextView text_overview;
        TextView text_genre1;
        TextView text_genre2;

        FilmeViewHolder(@NonNull View itemView) {
            super(itemView);

            imagePoster = itemView.findViewById(R.id.image_poster);
            textTitulo = itemView.findViewById(R.id.text_title);
            textNota = itemView.findViewById(R.id.text_rating);
            text_year = itemView.findViewById(R.id.text_year);
            text_overview = itemView.findViewById(R.id.text_overview);
            text_genre1 = itemView.findViewById(R.id.text_genre1);
            text_genre2 = itemView.findViewById(R.id.text_genre2);
        }
    }
}
