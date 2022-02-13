package com.example.e_wallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class profile extends AppCompatActivity {
TextView log_out, income, myName;
de.hdodenhof.circleimageview.CircleImageView profileImage;
EditText earnings;
FirebaseAuth mAuth;
FirebaseDatabase FD;
DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        log_out = findViewById(R.id.log_out);
        income = findViewById(R.id.income);
        myName = findViewById(R.id.myName);
        earnings = findViewById(R.id.earnings);
        profileImage = findViewById(R.id.profileImage);
        mAuth = FirebaseAuth.getInstance();

        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Toast.makeText(profile.this, "Signed out", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(profile.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String money = earnings.getText().toString();
                if(money.isEmpty()){
                    Toast.makeText(profile.this, "Give a value", Toast.LENGTH_SHORT).show();
                }else{
                    FD = FirebaseDatabase.getInstance();
                    reference = FD.getReference("Income");
                    String key=reference.push().getKey();
                    reference.child(key).setValue(money);
                    Toast.makeText(profile.this, "Income Added", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}