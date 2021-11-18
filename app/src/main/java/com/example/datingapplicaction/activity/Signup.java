package com.example.datingapplicaction.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.datingapplicaction.R;

public class Signup extends AppCompatActivity {

    EditText signupname,signupemail,signuppassword;

    ImageButton signup;
    Button signuptosignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupname = findViewById(R.id.signupname);
        signupemail = findViewById(R.id.signupemail);
        signuppassword = findViewById(R.id.loginpassword);
        signup = findViewById(R.id.signup);

        signuptosignin = findViewById(R.id.signuptosignin);

        signuptosignin.setOnClickListener(v -> {
            Intent signuptosignin = new Intent(Signup.this,login.class);
            startActivity(signuptosignin);
            finish();
        });
    }
}