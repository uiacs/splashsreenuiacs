package com.example.splashsreenuiacs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class logout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        Button buttonZ;

        buttonZ = (Button) findViewById(R.id.rectangle_no);
        buttonZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent qr = new Intent(logout.this, home.class);
                startActivity(qr);
            }
        });
    }
}