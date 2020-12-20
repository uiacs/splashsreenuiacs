package com.example.splashsreenuiacs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button buttonE;
        Button buttonF;
        Button buttonG;
        Button buttonH;

        buttonE = (Button) findViewById(R.id.qrcodebuttonhome);
        buttonF = (Button) findViewById(R.id.logouthome);
        buttonG = (Button) findViewById(R.id.kalenderhome);
        buttonH = (Button) findViewById(R.id.kelashome);

        buttonE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent qr = new Intent(home.this, qrcode.class);
                startActivity(qr);
            }
        });

        buttonF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent qr = new Intent(home.this, logout.class);
                startActivity(qr);
            }
        });

        buttonG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent qr = new Intent(home.this, kalender.class);
                startActivity(qr);
            }
        });

        buttonH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent qr = new Intent(home.this, kelas.class);
                startActivity(qr);
            }
        });
    }
}