package com.example.splashsreenuiacs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class register extends AppCompatActivity {

    Button buttonC;
    ImageButton buttonD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        buttonC = (Button) findViewById(R.id.btn_register);
        buttonD = findViewById(R.id.kelogin);

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent home = new Intent(register.this, home.class);
                startActivity(home);
                finish();
            }
        });
        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent register = new Intent(register.this, login.class);
                startActivity(register);
                finish();
            }
        });
    }
}