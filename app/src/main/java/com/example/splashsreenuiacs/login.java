package com.example.splashsreenuiacs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {
    Button buttonA;
    Button buttonB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        buttonA = (Button) findViewById(R.id.btn_login);
        buttonB = (Button) findViewById(R.id.tombolkanan);

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent home = new Intent(login.this, home.class);
                startActivity(home);
                finish();
            }
        });
        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent register = new Intent(login.this, register.class);
                startActivity(register);
                finish();
            }
        });

    }
}