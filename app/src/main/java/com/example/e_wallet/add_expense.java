package com.example.e_wallet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class add_expense extends AppCompatActivity {
EditText expense, category, note;
TextView date;
TextView save;
String exp, cat, not, tarikh;
FirebaseDatabase FD;
DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        expense = findViewById(R.id.expense);
        category = findViewById(R.id.category);
        note = findViewById(R.id.earnings);
        date = findViewById(R.id.date);
        save = findViewById(R.id.save);


        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);



        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        add_expense.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month +1;
                        String data = dayOfMonth+"/"+month+"/"+year;
                        date.setText(data);

                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exp = expense.getText().toString();
                cat = category.getText().toString();
                not = note.getText().toString();
                tarikh = date.getText().toString();
                String image;

                if(exp.isEmpty()){
                    Toast.makeText(add_expense.this, "Give an amount", Toast.LENGTH_SHORT).show();
                }else if(cat.isEmpty()){
                    Toast.makeText(add_expense.this, "Provide categoty name", Toast.LENGTH_SHORT).show();
                }else if(not.isEmpty()){
                    Toast.makeText(add_expense.this, "Give a sticky note", Toast.LENGTH_SHORT).show();
                }else if(tarikh.isEmpty()){
                    Toast.makeText(add_expense.this, "select a date", Toast.LENGTH_SHORT).show();
                }else if(exp == "" && cat == "" && not == "" && tarikh == ""){
                    Toast.makeText(add_expense.this, "Give required informations", Toast.LENGTH_SHORT).show();
                }else{

                    if(cat.equals("Food") || cat.equals("food"))
                        image="https://firebasestorage.googleapis.com/v0/b/finance-tracking-app-e9fb7.appspot.com/o/food2.jpg?alt=media&token=87929817-a721-47b6-ba69-d4abc0fee968";
                    else if(cat.equals("Living") || cat.equals("living"))
                        image="https://firebasestorage.googleapis.com/v0/b/finance-tracking-app-e9fb7.appspot.com/o/living.jpg?alt=media&token=57acecaa-6af2-4d99-a9c8-2819af987228";
                    else if(cat.equals("Health") || cat.equals("health"))
                        image="https://firebasestorage.googleapis.com/v0/b/finance-tracking-app-e9fb7.appspot.com/o/health2.png?alt=media&token=53d10e14-6c19-4c1e-810c-c1a63ae9f355";
                    else if(cat.equals("Other") || cat.equals("other"))
                        image="https://firebasestorage.googleapis.com/v0/b/finance-tracking-app-e9fb7.appspot.com/o/other2.png?alt=media&token=a1891f09-df6e-4ccb-9904-633952306e51";
                    else if(cat.equals("Shopping") || cat.equals("shopping"))
                        image="https://firebasestorage.googleapis.com/v0/b/finance-tracking-app-e9fb7.appspot.com/o/shopping.png?alt=media&token=ee4c3386-3460-4f62-8182-e7a3379f8833";
                    else{
                        image="";
                    }
                    expense_details myExpense;
                    if(image.equals("")) {
                        myExpense = new expense_details(exp, cat, not, tarikh);
                    }
                    else{
                        myExpense = new expense_details(exp, cat, not, tarikh,image);
                    }

                    FD = FirebaseDatabase.getInstance();
                    reference = FD.getReference("Activity");
                    String key=reference.push().getKey();
                    reference.child(key).setValue(myExpense);
                    Toast.makeText(add_expense.this, "Expense Added", Toast.LENGTH_SHORT).show();
                }




            }

        });


    }
}