package com.example.a04;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.a04.fragments.Descobrir_fragment;
import com.example.a04.fragments.Favorito_fragment;
import com.example.a04.fragments.Pesquisar_fragment;
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
        navegacao.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                Fragment fragment_selecionado = null;

                int id = menuItem.getItemId();

                if (id == R.id.acaoLista) {
                    fragment_selecionado = new Favorito_fragment();
                } else if (id == R.id.acaoDescobrir) {
                    fragment_selecionado = new Descobrir_fragment();
                } else if (id == R.id.acaoPesquisar){
                    fragment_selecionado = new Pesquisar_fragment();
                } else {
                    fragment_selecionado = new Recomendados_fragment();
                }

                if(fragment_selecionado != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container , fragment_selecionado)
                            .commit();
                }

                return true;
            }
        });

        if (savedInstanceState == null) {
            navegacao.setSelectedItemId(R.id.acaoDescobrir);
        }

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
