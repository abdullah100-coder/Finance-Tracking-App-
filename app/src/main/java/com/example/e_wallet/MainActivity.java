package com.example.e_wallet;

import static java.lang.Integer.parseInt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView myProfile;
    TextView plus, totalExp, myIncome, totalBalance, viewAll, myself;
    de.hdodenhof.circleimageview.CircleImageView picture;
    ImageView settings, notes, notification;
    ListView listView;
    ArrayList<expense_details> list;
int Totalsum;
int totalIncome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myProfile = findViewById(R.id.myProfile);
        plus = findViewById(R.id.plus);
        picture = findViewById(R.id.picture);
        totalExp = findViewById(R.id.totalExp);
        listView = findViewById(R.id.listView);
        myIncome = findViewById(R.id.myIncome);
        totalBalance = findViewById(R.id.totalBalance);
        settings = findViewById(R.id.settings);
        notes = findViewById(R.id.notes);
        notification = findViewById(R.id.notification);
        viewAll = findViewById(R.id.viewAll);
        myself = findViewById(R.id.myself);


        //CODE for first run
        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);

        if (isFirstRun) {
            //show start activity

            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).commit();


        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, add_expense.class);
                startActivity(intent);

            }
        });

        myProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, profile.class);
                startActivity(intent);
            }
        });
        notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, notes.class);
                startActivity(intent);
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, settings.class);
                startActivity(intent);
            }
        });
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, notification.class);
                startActivity(intent);
            }
        });
        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, view_all_list.class);
                startActivity(intent);
            }
        });




        //Code for retriving data from firebase to listView

        list = new ArrayList<>();
        CustomAdapter adapter = new CustomAdapter(getApplication(), list);
        listView.setAdapter(adapter);


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Activity");
        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference().child("Income");

        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                totalIncome=0;
                for(DataSnapshot data:snapshot.getChildren()){
                    String income=data.getValue(String.class);
                    totalIncome+= parseInt(income);
                }
                myIncome.setText(""+totalIncome);
                calBal();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    expense_details info = snapshot.getValue(expense_details.class);

                    list.add(info);

                }
                adapter.notifyDataSetChanged();
                calculateTotalExp();
                calBal();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    private void TotalAmount() {



    }

    private void calculateTotalExp() {
        Totalsum = 0;
        for (int i = 0; i < list.size(); i++) {

           Totalsum += parseInt(list.get(i).getExpense());
        }
        totalExp.setText("" + Totalsum);
    }
    private void calBal(){
        int blnc=totalIncome-Totalsum;
        totalBalance.setText(""+blnc);
    }
}