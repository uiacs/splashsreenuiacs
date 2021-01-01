package com.example.splashsreenuiacs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class logout extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        Button buttonZ;
        Button buttonY;

        buttonZ = (Button) findViewById(R.id.rectangle_no);
        buttonY = (Button) findViewById(R.id.rectangle_yes);
        buttonZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent home = new Intent(logout.this, home.class);
                startActivity(home);
                finish();
            }
        });

        buttonY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent login = new Intent(logout.this, login.class);
                startActivity(login);
                finish();
            }
        });
        mAuth = FirebaseAuth.getInstance();
    }

}