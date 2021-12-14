package com.example.datingapplicaction.activity;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;


import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import com.example.datingapplicaction.Model.User;
import com.example.datingapplicaction.R;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Signup extends AppCompatActivity {

    EditText signupname,signupemail,signuppassword;

    Button newsignup;
    Button signuptosignin;

    FirebaseAuth mAuth;

    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupname = findViewById(R.id.signupname);
        signupemail = findViewById(R.id.signupemail);
        signuppassword = findViewById(R.id.signuppassword);
        newsignup = findViewById(R.id.signup);

        signuptosignin = findViewById(R.id.signuptosignin);
        mAuth = FirebaseAuth.getInstance();









        signuptosignin.setOnClickListener(v -> {
            Intent signuptosignin = new Intent(Signup.this,login.class);
            startActivity(signuptosignin);
            finish();
        });

        newsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createnewUser();

            }
        });





    }

    private void createnewUser() {

        String name = signupname.getText().toString();
        String email = signupemail.getText().toString();
        String password = signuppassword.getText().toString();
        if (!name.isEmpty()){
            if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                if (!password.isEmpty()){
                    if (!(password.length() <8)){

                        reagesternewUser(email,password ,name);



                    }else {
                        signuppassword.setError("Password must be more than 8 digit");
                    }
                }else {
                    signuppassword.setError("Please enter your Password");
                }
            }else if (email.isEmpty()){
                signupemail.setError("Please enter your Email");
            }else {
                signupemail.setError("Please enter your Correct E-mail address");
            }
        }else {
            signupname.setError("Please enter your Name");
        }

    }

    private void reagesternewUser(String email,String password, String name) {

        reference = FirebaseDatabase.getInstance().getReference().child("User");

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    FirebaseUser firebaseUser  = mAuth.getCurrentUser();
                    User user = new User(name,email,password);

                    reference.child(firebaseUser.getUid()).setValue(user);
                    Intent i = new Intent(Signup.this,MainScreenActivity.class);
                    startActivity(i);
                    finish();

                    
                }
                else {
                    Toast.makeText(getApplicationContext(), "Email Already Registered", Toast.LENGTH_SHORT).show();
                }
                

            }
        });
    }


}