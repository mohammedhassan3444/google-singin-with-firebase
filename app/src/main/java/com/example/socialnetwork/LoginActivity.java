package com.example.socialnetwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity
{

    private Button LoginButton;
    private EditText UserEmail, UserPassword;
    private TextView NeedNewAccuntLink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        NeedNewAccuntLink = (TextView) findViewById(R.id.Createa_new_account);
        UserEmail = (EditText) findViewById(R.id.email_login);
        UserPassword = (EditText) findViewById(R.id.Password_login);
        LoginButton = (Button) findViewById(R.id.button_login);

        NeedNewAccuntLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SendUserToRegisterActivity();

            }
        });






    }

    private void SendUserToRegisterActivity()
    {
        Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(registerIntent);
    }
}