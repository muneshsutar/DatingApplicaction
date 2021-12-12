package com.example.datingapplicaction.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.datingapplicaction.MainActivity;
import com.example.datingapplicaction.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    EditText loginemail,loginpassword;
    ImageView signin;
    Button signup;

    TextView forgotPassword;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginemail = findViewById(R.id.loginemail);
        loginpassword = findViewById(R.id.loginpassword);
        signin = findViewById(R.id.signin);
        signup = findViewById(R.id.signup);

        forgotPassword = findViewById(R.id.forgotPassword);
        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() !=null){
            Intent intent = new Intent(login.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }



        signup.setOnClickListener(v -> {
            Intent signintent = new Intent(login.this,Signup.class);
            startActivity(signintent);

        });

        signin.setOnClickListener(v -> {
            String logemail = loginemail.getText().toString();
            String logpass = loginpassword.getText().toString();
            if (!logemail.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(logemail).matches()){
                if (!logpass.isEmpty()){
                    firebaseAuth.signInWithEmailAndPassword(logemail,logpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Login SuccessesFully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(login.this,HomeActivity.class);
                                startActivity(intent);
                                finish();


                            }else {
                                Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }else {
                    loginpassword.setError("Empty Fields Are not Allowed");
                }
            }else if (logemail.isEmpty()){
                loginemail.setError("Empty Fields Are not Allowed");

            }else {
                loginemail.setError("Please Enter Correct Gmail");

            }
        });


        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(login.this,ForgotPassword.class);
               startActivity(intent);
               finish();



            }
        });
























    }
}