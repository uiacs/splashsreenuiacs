package com.example.splashsreenuiacs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class inputcode extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputcode);
        EditText codelayout;
        EditText name;
        Button btn1;

        codelayout = (EditText) findViewById(R.id.code1);
        name = (EditText) findViewById(R.id.name1);
        btn1 = (Button) findViewById(R.id.btn_login);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//instansiasi database firebase
                FirebaseDatabase database = FirebaseDatabase.getInstance();

//Referensi database yang dituju
                DatabaseReference myRef = database.getReference("code").child(codelayout.getText().toString());;

//memberi nilai pada referensi yang dituju
                myRef.child("name").setValue(name.getText().toString());

                Intent home = new Intent(inputcode.this, home.class);
                startActivity(home);

            }
        });


    }
}