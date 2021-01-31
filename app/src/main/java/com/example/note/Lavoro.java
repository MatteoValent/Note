package com.example.note;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Lavoro extends AppCompatActivity {     //gestore della scelta del tipo di nota (lavoro)

    //varie dichiarazioni
    Button buttonSalvaLavoro;
    EditText TitoloLavoro, Contenuto;
    String  titolo, contenuto;
    String tipo = "Lavoro";

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lavoro);
        Intent lavoro = getIntent();

        TitoloLavoro = findViewById(R.id.EditTextTitoloLavoro);
        Contenuto = findViewById(R.id.EditTextContenutoLavoro);

        buttonSalvaLavoro = findViewById(R.id.btnSalvaLavoro);
        buttonSalvaLavoro.setOnClickListener(new View.OnClickListener() { //bottone salva nota
            public void onClick(View v) {
                GestoreDB myDB = new GestoreDB(Lavoro.this);
                titolo = TitoloLavoro.getText().toString().trim();
                contenuto = Contenuto.getText().toString().trim();
                myDB.AggiuntaNota(tipo, titolo, contenuto);     //richiamo la funzione per l'aggiunta della nota presente nel gestoreDB con i nuovi parametri

                Intent salvaLavoro = new Intent(Lavoro.this,MainActivity.class);
                startActivity(salvaLavoro);
            }
        });
    }

}