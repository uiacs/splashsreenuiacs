package com.example.splashsreenuiacs;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class register extends AppCompatActivity {
    private static final String TAG = "login";
    private EditText inputEmail, inputPassword, rePassword, inputFname, inputNPM;
    private FirebaseAuth mAuth;
    Button buttonReg;
    ImageButton buttonD;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        buttonReg = findViewById(R.id.btn_register);
        buttonD = findViewById(R.id.kelogin);
        inputFname = findViewById(R.id.et_name);
        inputNPM = findViewById(R.id.et_npm);
        inputEmail = findViewById(R.id.et_email);
        inputPassword = findViewById(R.id.et_password);
        rePassword = findViewById(R.id.et_repassword);

        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                String rePasswrd = rePassword.getText().toString().trim();
                String Fname = inputFname.getText().toString().trim();
                String npm = inputNPM.getText().toString().trim();

                mAuth = FirebaseAuth.getInstance();
                fStore = FirebaseFirestore.getInstance();

                if (TextUtils.isEmpty(Fname)) {
                    Toast.makeText(getApplicationContext(),
                            "Please enter your name!!",
                            Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                if (TextUtils.isEmpty(npm)) {
                    Toast.makeText(getApplicationContext(),
                            "Please enter your NPM!",
                            Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(),
                            "Please enter email!!",
                            Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(),
                            "Please enter password!!",
                            Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                if (password.length() < 6) {
                    inputPassword.setError("Password must be at least 6 characters!");
                    return;
                }

                if (TextUtils.isEmpty(rePasswrd)) {
                    Toast.makeText(getApplicationContext(),
                            "Please re-enter password!!",
                            Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                if (!password.equals(rePasswrd)) {
                    Toast.makeText(getApplicationContext(),
                            "Password aren't the same, try again",
                            Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
                    @Override

                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(register.this, "Register Success.", Toast.LENGTH_SHORT).show();

                            userID = mAuth.getCurrentUser().getUid();

                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("nama", Fname);
                            user.put("npm", npm);
                            user.put("email", email);

                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: user profile created for "+ userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error adding document", e);
                                }
                            });

                            Intent login = new Intent(register.this, home.class);
                            //FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(login);
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(register.this, "Register failed.", Toast.LENGTH_SHORT).show();
                            Intent register = new Intent(register.this, login.class);
                            startActivity(register);

                        }
                    }
                });
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