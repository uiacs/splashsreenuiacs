package com.example.splashsreenuiacs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class profile extends AppCompatActivity {

    private static final String TAG = "profile";
    TextView fullname, email, fakultas, jurusan, npm;
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button updateProfile = findViewById(R.id.updateprofile);
        fullname = findViewById(R.id.nama);
        npm = findViewById(R.id.npm);
        email = findViewById(R.id.email);
        fakultas = findViewById(R.id.fakultas);
        jurusan = findViewById(R.id.jurusan);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userID = mAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {

                fullname.setText(documentSnapshot.getString("nama"));
                npm.setText(documentSnapshot.getString("npm"));
                email.setText(documentSnapshot.getString("email"));
                jurusan.setText(documentSnapshot.getString("jurusan"));
                fakultas.setText(documentSnapshot.getString("fakultas"));

            }
        });

        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent editprofile = new Intent(profile.this, editprofile.class);
                startActivity(editprofile);
                finish();

            }
        });
    }
}