package com.example.a04;

import static android.view.View.VISIBLE;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
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
import androidx.fragment.app.Fragment;

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
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navegacao;

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
        navegacao = findViewById(R.id.navegacao);

        navegacao.setSelectedItemId(R.id.acaoDescobrir);

        navegacao.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                Fragment fragment_selecionado = null;

                int id = menuItem.getItemId();

                if (id == R.id.acaoLista) {
                    fragment_selecionado = new Lista_fragment();
                } else if (id == R.id.acaoDescobrir) {
                    fragment_selecionado = new Descobrir_fragment();
                } else {
                    fragment_selecionado = new Pesquisar_fragment();
                }

                if(fragment_selecionado != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container , fragment_selecionado)
                            .commit();
                }

                return true;
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
