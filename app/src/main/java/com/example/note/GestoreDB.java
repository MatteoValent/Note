package com.example.note;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class GestoreDB extends SQLiteOpenHelper {

    //varie dichiarazioni
    private Context context;
    private static final String NoteDB = "Note.db";
    private static final int Versione = 1;

    private static final String NomeTabella = "Note";
    private static final String IDnota = "ID";
    private static final String TipoNota = "Tipo";
    private static final String TitoloNota = "Titolo";
    private static final String ContenutoNota = "Contenuto";


    GestoreDB(@Nullable Context context) {  //costruttore
        super(context, NoteDB, null, Versione);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)     //on crete per la creazione della tabella nel db e la eseguo
    {
        String query = "CREATE TABLE " + NomeTabella +
                " (" + IDnota + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TipoNota + " TEXT, " +
                TitoloNota + " TEXT, " +
                ContenutoNota + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}


    void AggiuntaNota(String tipo, String titolo, String contenuto) //funzione per l'aggiunta delle note
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues riga = new ContentValues();

        if(titolo.equals("")){          //in caso che la stringa del titolo sia vuota assegno una scritta
            titolo = "senza titolo";
        }
        if(contenuto.equals("")){       //in caso che la stringa del contenuto sia vuota assegno una scritta
            contenuto = "nota vuota";
        }
            riga.put(TipoNota, tipo);      //aggiungo il tipo di nota
            riga.put(TitoloNota, titolo);   //aggiungo il titolo della nota
            riga.put(ContenutoNota, contenuto);  //aggiunge il contenuto della nota
            long result = db.insert(NomeTabella, null, riga);
            if (result == -1) {     //controllo in caso che il caricamento non fosse avvenuto con successo esce il toast
                Toast.makeText(context, "Aggiunta fallita", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Aggiunta avvenuta", Toast.LENGTH_SHORT).show();
            }
    }

    Cursor LeggiTutto()
    {
        String query = "SELECT * FROM " + NomeTabella;       //comando che mi seleziona tutto dalla tabella prescelta
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;       //istanzio il cursore a null
        if(db != null)   //faccio il controllo per vedere che il db abbia elementi all'interno
        {
            cursor = db.rawQuery(query, null);   //query è stata istanziata nel on create
        }
        return cursor;  //ritorna il contenuto della raw query
    }

    void CancellaNote()         //funzione che elimina tutto il contenuto del db
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + NomeTabella);       //comando sql che cancella tutto dalla tabella scelta
    }
}

