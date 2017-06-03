package com.example.laksh.neutro_applicationc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
ImageButton btnCalculate, btnStatistics, btnReminder, btnLogout;
TextView ivProfile, User;
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
        btnCalculate = (ImageButton) findViewById(R.id.btnCalculate);
        btnStatistics = (ImageButton) findViewById(R.id.btnStatistics);
        btnReminder = (ImageButton) findViewById(R.id.btnReminder);
        btnLogout = (ImageButton) findViewById(R.id.btnLogout);
        ivProfile = (TextView) findViewById(R.id.txtProfile);
        User = (TextView) findViewById(R.id.txtUser) ;
        ProfileActivity pa = new ProfileActivity();
        User.setText(pa.UserName);
       btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CalculateActivity.class));
            }
        });
        btnStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StatisticsActivity.class));
            }
        });
        ivProfile.setOnClickListener(this);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v == ivProfile){
            startActivity(new Intent(this, ProfileActivity.class));
        }
    }
}
