package com.example.a04;

import static android.view.View.VISIBLE;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.a04.api.Filme;
import com.example.a04.api.FilmeApi;
import com.example.a04.api.Results;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.example.a04.BuildConfig;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private TextView txtFilme;
    private String chaveApi = BuildConfig.TMDB_API_KEY;
    private String idioma = "pt-BR";
    private SearchView seaPesquisar;
    private FilmeApi api;
    private Retrofit retrofit;
    private ProgressBar progress_bar;

    // VARIÁVEIS DA API
    private String descricao;
    private String titulo;
    private ImageView imgPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txtFilme = findViewById(R.id.txtFilme);
        seaPesquisar = findViewById(R.id.seaPesquisar);
        progress_bar = findViewById(R.id.progress_bar);
        imgPoster = findViewById(R.id.imgPoster);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(FilmeApi.class);

        seaPesquisar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                pesquisarFilmes();
                progress_bar.setVisibility(View.VISIBLE);
                return true;
            }
        });
    }

    private void pesquisarFilmes() {
        String nomeFilme = seaPesquisar.getQuery().toString();
        Call<Results> chamada = api.buscarFilme(chaveApi, idioma, nomeFilme);

        chamada.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {
                txtFilme.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    Filme filmeEncontrado = response.body().getResultados().get(0);
                    titulo = filmeEncontrado.getTitulo();
                    descricao = filmeEncontrado.getDescricao();
                    String url_imagem = "https://image.tmdb.org/t/p/w500" + filmeEncontrado.getPoster();

                    Glide.with(MainActivity.this).load(url_imagem).centerCrop().listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, @Nullable Object model
                                , @NonNull Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(@NonNull Drawable resource, @NonNull Object model
                                , Target<Drawable> target, @NonNull DataSource dataSource, boolean isFirstResource) {
                            progress_bar.setVisibility(View.GONE);
                            txtFilme.setText(titulo + ": " + descricao);
                            imgPoster.setVisibility(View.VISIBLE);
                            txtFilme.setVisibility(View.VISIBLE);
                            return false;
                        }
                    }).into(imgPoster);
                }
            }

            @Override
            public void onFailure(Call<Results> call, Throwable t) {
                txtFilme.setText("Filme não encontrado");
                progress_bar.setVisibility(View.GONE);
                txtFilme.setVisibility(View.VISIBLE);
                imgPoster.setVisibility(View.GONE);
            }
        });
    }

    private void trocarTela(Class novaTela) {
        Intent i = new Intent(getApplicationContext(), novaTela);
        startActivity(i);
        finish();
    }

    public void logout(View v) {
        FirebaseAuth.getInstance().signOut();
        trocarTela(TelaLogin.class);
    }

}