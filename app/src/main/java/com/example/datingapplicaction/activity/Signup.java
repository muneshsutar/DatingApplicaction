package com.example.datingapplicaction.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.datingapplicaction.R;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {

    EditText signupname,signupemail,signuppassword;

    ImageButton signup;
    Button signuptosignin;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupname = findViewById(R.id.signupname);
        signupemail = findViewById(R.id.signupemail);
        signuppassword = findViewById(R.id.loginpassword);
        signup = findViewById(R.id.signup);

        signuptosignin = findViewById(R.id.signuptosignin);
        firebaseAuth = FirebaseAuth.getInstance();

        signuptosignin.setOnClickListener(v -> {
            Intent signuptosignin = new Intent(Signup.this,login.class);
            startActivity(signuptosignin);
            finish();
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String signname = signupname.getText().toString();
                String signemail = signupemail.getText().toString();
                String signpass = signuppassword.getText().toString();

                if (!signname.isEmpty()){
                    if (!signemail.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(signemail).matches() ){
                        if (!signpass.isEmpty()){



                        }else {
                            signuppassword.setError("Empty Fields Are not Allowed");
                        }
                    }else if (signemail.isEmpty()){
                        signupemail.setError("Empty Fields Are not Allowed");
                    }else {
                        signupemail.setError("Please Enter Correct Gmail");
                    }
                }else {
                    signupname.setError("Empty Fields Are not Allowed");
                }
            }
        });




    }
}