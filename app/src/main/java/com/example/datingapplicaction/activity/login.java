package com.example.datingapplicaction.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.datingapplicaction.R;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    EditText loginemail,loginpassword;
    ImageView signin;
    Button signup;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginemail = findViewById(R.id.loginemail);
        loginpassword = findViewById(R.id.loginpassword);
        signin = findViewById(R.id.signin);
        signup = findViewById(R.id.signup);


        signup.setOnClickListener(v -> {
            Intent signintent = new Intent(login.this,Signup.class);
            startActivity(signintent);

        });






    }
}