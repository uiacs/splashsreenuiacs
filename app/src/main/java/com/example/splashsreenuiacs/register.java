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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class register extends AppCompatActivity {
    private static final String TAG = "login";
    private EditText inputEmail, inputPassword, rePassword, inputUsername;
    private FirebaseAuth mAuth;
    Button buttonReg;
    ImageButton buttonD;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        buttonReg = findViewById(R.id.btn_register);
        buttonD = findViewById(R.id.kelogin);
        inputUsername = findViewById(R.id.et_name);
        inputEmail = findViewById(R.id.et_email);
        inputPassword = findViewById(R.id.et_password);
        rePassword = findViewById(R.id.et_repassword);

        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                String rePasswrd = rePassword.getText().toString().trim();
                String username = inputUsername.getText().toString().trim();

                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(getApplicationContext(),
                            "Please enter your username!!",
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
                if (TextUtils.isEmpty(rePasswrd)) {
                    Toast.makeText(getApplicationContext(),
                            "Please re-enter password!!",
                            Toast.LENGTH_LONG)
                            .show();
                    return;
                }
                if (!password.equals(rePasswrd)) {
                    Toast.makeText(getApplicationContext(),
                            "Password aren't the same!",
                            Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)

                        .addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
                            @Override


                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    Toast.makeText(register.this, "Register Success.",
                                            Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Intent login = new Intent(register.this, home.class);
                                    startActivity(login);
                                    finish();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(register.this, "Register failed.",
                                            Toast.LENGTH_SHORT).show();
                                    Intent register
                                            = new Intent(register.this,
                                            login.class);
                                    startActivity(register);

                                }

                                // ...
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
        mAuth = FirebaseAuth.getInstance();
    }

}