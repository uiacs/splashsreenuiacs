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

        buttonE = (Button) findViewById(R.id.qrcodebuttonhome);

        buttonE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent qr = new Intent(home.this, qrcode.class);
                startActivity(qr);
            }
        });
    }
}