package com.example.note;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ModificaNota extends AppCompatActivity { //classe non finita rigurdante la modifica delle varie note
    Button buttonSalva;
    EditText Titolo, Contenuto;
    String titolo, contenuto, id, tipoNota;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica);
        Titolo = findViewById(R.id.EditTextTitoloLavoroAggiorna);
        Contenuto = findViewById(R.id.EditTextContenutoLavoroAggiorna);
        buttonSalva = findViewById(R.id.btnSalvaLavoroAggiorna);
        buttonSalva.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

            }
        });
        DatiModifica();
    }
    void DatiModifica()
    {
        if(getIntent().hasExtra("id") && getIntent().hasExtra("tipo") &&
                getIntent().hasExtra("titolo") && getIntent().hasExtra("contenuto")) {
            id = getIntent().getStringExtra("id");
            tipoNota = getIntent().getStringExtra("tipo");
            titolo = getIntent().getStringExtra("titolo");
            contenuto = getIntent().getStringExtra("contenuto");

            Titolo.setText(titolo);
            Contenuto.setText(contenuto);
        }else{
            Toast.makeText(this, "errore", Toast.LENGTH_SHORT).show();
        }
    }

}
