package com.example.note;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    /*dichiarazioni*/
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    GestoreDB myDB;
    ArrayList<String> tipo, titolo, contenuto;
    ArrayList<Integer> id;
    AggiuntaNote aggiuntaNote;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        /*barra laterale*/
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        /*bottone aggiungi nota*/
        final Button button = findViewById(R.id.btnAggiungi);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ScritturaNota.class);
                startActivity(i);
            }
        });
        /*bottone elimina note*/
        final Button btnCancella = findViewById(R.id.btnCancella);
        btnCancella.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "CANCELLATE TUTTE LE NOTE", Toast.LENGTH_SHORT).show();       //toast che avverte la cancellazione di tutte le immagini
                GestoreDB db = new GestoreDB(MainActivity.this);            //istanzio una variabile di tipo GestoreDB
                db.CancellaNote();   //richiamo funzione per eliminare le note dal database

                //comandi per refreshiare la pagina
                Intent i = new Intent(MainActivity.this,MainActivity.class);
                startActivity(i);
                finish();
;
            }
        });

        myDB = new GestoreDB(MainActivity.this);
        //ad ogni variabile assegno un arrayList
        id= new ArrayList<>();
        tipo = new ArrayList<>();
        titolo = new ArrayList<>();
        contenuto = new ArrayList<>();

        ArrayNote();        //richiamo la funzione per l'aggiunta delle note
        recyclerView= (RecyclerView) findViewById(R.id.recycleView);
        aggiuntaNote = new AggiuntaNote(this, id, tipo, titolo, contenuto);
        recyclerView.setAdapter(aggiuntaNote);      //aggiungo la nuova nota alla recycle view
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this)); //risetto il layout iniziale alla main activity

    }

    //Funzione per l'aggiunta note
    void ArrayNote(){
        Cursor c = myDB.LeggiTutto();  //istanzio una variabile di tipo cursor che mi serve come puntatore all'interno del db
        if(c.getCount() == 0){          //controllo se il db è vuoto e a questo punto faccio apparire un toast
            Toast.makeText(this, "Vuoto", Toast.LENGTH_SHORT).show();
        } else
        {
            while(c.moveToNext()){      //mi sposto nel db e aggiungo
                id.add(c.getInt(0));
                tipo.add(c.getString(1));
                titolo.add(c.getString(2));
                contenuto.add(c.getString(3));
            }
        }
    }

    //funzione che serve per una futura implementazione dei select item all'interno della navigation views
    public void onBackPressed()
    {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }else
        {
            super.onBackPressed();
        }
    }

    //override funzione che permette l'utilizzo della navigation view
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }
}