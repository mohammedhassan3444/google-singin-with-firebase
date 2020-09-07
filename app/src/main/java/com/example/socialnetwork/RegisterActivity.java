package com.example.socialnetwork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText UserEmail, UserPassword;
    private Button CreateAccountButton;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        UserEmail = findViewById(R.id.email_Register);
        UserPassword = findViewById(R.id.Password_Register);
        CreateAccountButton = findViewById(R.id.button_register);

        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar_register);

        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();

        }


        CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = UserEmail.getText() . toString().trim();
                String password = UserPassword.getText() .toString().trim();

                if (TextUtils.isEmpty(email)){
                    UserEmail.setError("Email is Required.");
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    UserPassword.setError("password is Required.");
                    return;
                }

                if (password.length() < 6){
                    UserPassword.setError("Password Must be >= 6 Characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                // register the user in firebase

                mAuth.createUserWithEmailAndPassword(email,password) .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "User Created.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else {
                            Toast.makeText(RegisterActivity.this, "Error!" + task.getException(), Toast.LENGTH_SHORT).show();


                        }

                    }
                });
            }
        });


    }
}