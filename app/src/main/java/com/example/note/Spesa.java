package com.example.note;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Spesa extends AppCompatActivity { //gestore della scelta del tipo di nota (spesa)

    //varie dichiarazioni
    Button buttonSalvaSpesa;
    EditText TitoloSpesa, Contenuto;
    String  titolo, contenuto;
    String tipo = "Spesa";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spesa);
        Intent spesa = getIntent();

        TitoloSpesa = findViewById(R.id.EditTextTitoloSpesa);
        Contenuto = findViewById(R.id.EditTextContenutoSpesa);

        buttonSalvaSpesa = findViewById(R.id.btnSalvaSpesa);
        buttonSalvaSpesa.setOnClickListener(new View.OnClickListener() { //bottone salva nota
            public void onClick(View v) {
                GestoreDB myDB = new GestoreDB(Spesa.this);
                titolo = TitoloSpesa.getText().toString().trim();
                contenuto = Contenuto.getText().toString().trim();
                myDB.AggiuntaNota(tipo, titolo, contenuto);        //richiamo la funzione per l'aggiunta della nota presente nel gestoreDB con i nuovi parametri

                Intent salvaSpesa = new Intent(Spesa.this,MainActivity.class);
                startActivity(salvaSpesa);
            }
        });
    }
}