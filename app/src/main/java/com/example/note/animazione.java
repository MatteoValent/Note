package com.example.note;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class animazione extends AppCompatActivity {

    private static final int t = 10000 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schermatahome);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(animazione.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        },t);

    }
}
