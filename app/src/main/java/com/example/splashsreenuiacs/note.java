package com.example.splashsreenuiacs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class note extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        Button buttonBB;

        buttonBB = (Button) findViewById(R.id.rectangle_untuk_notes);

        buttonBB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent qr = new Intent(note.this, detailnote.class);
                startActivity(qr);
            }
        });

    }
}