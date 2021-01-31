package com.example.note;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AggiuntaNote extends RecyclerView.Adapter<AggiuntaNote.MyViewHolder>
{
    private Context context;
    private ArrayList id, tipo, titolo, contenuto;
    Activity activity;

    //costrutore
    AggiuntaNote(Context context, ArrayList id, ArrayList tipo, ArrayList titolo, ArrayList contenuto){
        this.context = context;
        this.id = id;
        this.tipo = tipo;
        this.titolo = titolo;
        this.contenuto = contenuto;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)   //mi permette di aggiungere un oggetto di tipo view alla recycle view
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.riga, parent, false);  //il layout fa riferimento a quello della riga che si trova in riga.xml

        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position)          // mi attribuisce alle varie text area il contenuto dei vari campi
    {
        holder.txtID.setText(String.valueOf(id.get(position)));
        holder.txtTitolo.setText("  "+ String.valueOf(titolo.get(position)));
        holder.txtCont.setText(String.valueOf(contenuto.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

            }
        });

    }

    @Override
    public int getItemCount() {         //conta id

        return id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {       //mi fornisce il layout dove inserire le varie informazioni
        TextView txtTitolo, txtID, txtCont;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //dichiarazioni delle text area presenti in riga.xml
            txtID = itemView.findViewById(R.id.txtID);
            txtTitolo = itemView.findViewById(R.id.txtTitolo);
            txtCont = itemView.findViewById(R.id.txtCont);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
