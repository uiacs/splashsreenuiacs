package com.example.splashsreenuiacs;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class editprofile extends AppCompatActivity {
    private static final String TAG = "editprofile";
    private EditText fullname, email, fakultas, jurusan, npm;
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);

        Button saveProfile = findViewById(R.id.saveprofile);
        Button cancel = findViewById(R.id.cancel);
        fullname = findViewById(R.id.nama);
        npm = findViewById(R.id.npm);
        email = findViewById(R.id.email);
        fakultas = findViewById(R.id.fakultas);
        jurusan = findViewById(R.id.jurusan);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userID = mAuth.getCurrentUser().getUid();
        FirebaseUser user = mAuth.getCurrentUser();

        saveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newemail = email.getText().toString();
                String newname = fullname.getText().toString();
                String newnpm = npm.getText().toString();
                String newjurusan = jurusan.getText().toString();
                String newfakultas = fakultas.getText().toString();

                DocumentReference documentReference = fStore.collection("users").document(userID);
                Map<String, Object> edited = new HashMap<>();
                edited.put("nama", newname);
                edited.put("npm", newnpm);
                edited.put("email", newemail);
                edited.put("fakultas", newfakultas);
                edited.put("jurusan", newjurusan);

                documentReference.set(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "onSuccess: user profile updated for " + userID);
                        Toast.makeText(editprofile.this, "Profile updated", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating profile", e);
                        Toast.makeText(editprofile.this, "Profile update failed", Toast.LENGTH_SHORT).show();
                    }
                });
                Intent profile = new Intent(editprofile.this, profile.class);
                startActivity(profile);
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profile = new Intent(editprofile.this, profile.class);
                startActivity(profile);
                finish();
            }
        });

    }
}