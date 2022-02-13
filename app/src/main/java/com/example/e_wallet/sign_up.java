package com.example.e_wallet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class sign_up extends AppCompatActivity {
EditText email, password;
TextView signup;
String pemail, ppass;
private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signup = findViewById(R.id.addName);
        mAuth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               pemail = email.getText().toString().trim();
               ppass = password.getText().toString().trim();

               if(pemail.isEmpty()){
                   Toast.makeText(sign_up.this, "Give an email", Toast.LENGTH_SHORT).show();
               }else if(ppass.isEmpty()){
                   Toast.makeText(sign_up.this, "A password is needed", Toast.LENGTH_SHORT).show();
               }
               else if(ppass.length() <=5){
                   Toast.makeText(sign_up.this, "More than 6 digits needed", Toast.LENGTH_SHORT).show();
               }else if(pemail.length() <= 11){
                   Toast.makeText(sign_up.this, "Invalid email", Toast.LENGTH_SHORT).show();
               }else

                mAuth.createUserWithEmailAndPassword(pemail,ppass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(sign_up.this, "User successfully created", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(sign_up.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });


    }
}