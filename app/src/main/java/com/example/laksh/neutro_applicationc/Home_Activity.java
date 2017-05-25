package com.example.laksh.neutro_applicationc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Home_Activity extends AppCompatActivity {
ImageButton btnCalculate, btnStatistics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnCalculate = (ImageButton) findViewById(R.id.btnCalculate);
        btnStatistics = (ImageButton) findViewById(R.id.btnStats) ;
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home_Activity.this, CalculateActivity.class));
            }
        });
        btnStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home_Activity.this, StatisticsActivity.class));
            }
        });

    }
}
