package com.example.note;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ScritturaNota extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggiunta);
        Intent i = getIntent();

        final Button buttonSpesa = findViewById(R.id.btnSpesa);  //bottone che mi porta ad inserire i dati da inserire nel db nella sezione spesa
        buttonSpesa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent spesa = new Intent(ScritturaNota.this,Spesa.class);
                startActivity(spesa);
            }
        });
        final Button buttonLavoro = findViewById(R.id.btnLavoro);   //bottone che mi porta ad inserire i dati da inserire nel db nella sezione lavoro
        buttonLavoro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent lavoro = new Intent(ScritturaNota.this,Lavoro.class);
                startActivity(lavoro);
            }
        });

        Intent salvaSpesa = getIntent();
        Intent salvaLavoro = getIntent();
    }

}
