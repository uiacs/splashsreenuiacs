package com.example.splashsreenuiacs;


import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity  {
    private int waktu_loading=4000;
    private FirebaseAuth mAuth;
    //4000=4 detik

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                FirebaseUser mFirebaseUser = mAuth.getCurrentUser();
                if(mFirebaseUser!=null){
                    Toast.makeText(MainActivity.this, "You're signed in",
                            Toast.LENGTH_SHORT).show();
                    Intent home=new Intent(MainActivity.this, home.class);
                    startActivity(home);
                }else{
                    Toast.makeText(MainActivity.this, "Please login first",
                            Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, login.class));
                    finish();
                }
            }
        },waktu_loading);
        mAuth= FirebaseAuth.getInstance();
    }

}