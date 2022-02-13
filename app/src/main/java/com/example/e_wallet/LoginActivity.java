package com.example.e_wallet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
TextView login, lsignup;
EditText lemail, lpass;
String a, b;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        lsignup = findViewById(R.id.lsignup);
        login = findViewById(R.id.addName);
        lemail = findViewById(R.id.profile_name);
        lpass = findViewById(R.id.lpass);
        mAuth = FirebaseAuth.getInstance();

        lsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this,sign_up.class);
                startActivity(intent);


            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a = lemail.getText().toString().trim();
                b = lpass.getText().toString().trim();
                if(a.isEmpty() || b.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Field can't be empty", Toast.LENGTH_SHORT).show();
                }else
                mAuth.signInWithEmailAndPassword(a,b).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);

                        }else{
                            Toast.makeText(LoginActivity.this, "User doesn't exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });



    }
}