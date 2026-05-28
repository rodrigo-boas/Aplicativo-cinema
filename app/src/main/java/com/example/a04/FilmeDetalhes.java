package com.example.a04;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class FilmeDetalhes extends AppCompatActivity {

    private ImageView image_poster_detail;
    private TextView text_title_detail;
    private TextView text_rating_detail;
    private TextView text_year_detail;
    private TextView text_overview_detail;
    private TextView btnFechar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_filme_detalhes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        image_poster_detail = findViewById(R.id.image_poster_detail);
        text_title_detail = findViewById(R.id.text_title_detail);
        text_rating_detail = findViewById(R.id.text_rating_detail);
        text_year_detail = findViewById(R.id.text_year_detail);
        text_overview_detail = findViewById(R.id.text_overview_detail);
        btnFechar = findViewById(R.id.btnFechar);

        String titulo = getIntent().getStringExtra("titulo");
        String sinopse = getIntent().getStringExtra("descricao");
        String posterUrl = getIntent().getStringExtra("poster");
        String data = getIntent().getStringExtra("data");
        double nota = getIntent().getDoubleExtra("nota", 0.0);

        text_title_detail.setText(titulo);
        text_rating_detail.setText(String.format("%.1f", nota));
        text_overview_detail.setText(sinopse);
        text_year_detail.setText(data.substring(0, 4));

        String urlCompleta = "https://image.tmdb.org/t/p/w500" + posterUrl;

        Glide.with(getApplicationContext()).load(urlCompleta).into(image_poster_detail);

        btnFechar.setOnClickListener(v -> finish());


    }
}