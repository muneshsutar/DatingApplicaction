package com.example.datingapplicaction.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.datingapplicaction.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    TextView alertmessages;

    ProgressBar resetProgress;

    EditText inputGmail;
    ImageView backArrow;
    ImageButton forgotbtn;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        inputGmail = findViewById(R.id.inputGmail);
        forgotbtn = findViewById(R.id.forgotbtn);
        backArrow = findViewById(R.id.backArrow);
        alertmessages = findViewById(R.id.alertmessages);
        resetProgress = findViewById(R.id.resetProgress);

        firebaseAuth = FirebaseAuth.getInstance();

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPassword.this,login.class);
                startActivity(intent);
                finish();
            }
        });
        forgotbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputGmail.getText().toString().trim();
                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    resetProgress.setVisibility(View.VISIBLE);
                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                resetProgress.setVisibility(View.GONE);

                                alertmessages.setText("We have sent you instructions to reset your password!");

                            } else {
                                resetProgress.setVisibility(View.GONE);
                                alertmessages.setText("Failed to send reset email!");

                            }
                        }
                    });


                }else if (email.isEmpty()){
                    inputGmail.setError("Empty Fields Are not Allowed");
                }else {
                    inputGmail.setError("Enter your registered email id");
                }
            }
        });

    }
}