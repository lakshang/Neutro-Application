package com.example.laksh.neutro_applicationc;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Spalsh_Screen extends AppCompatActivity {
    private static int splash_timeout = 2500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh__screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent register = new Intent(Spalsh_Screen.this, CalculateActivity.class);
                startActivity(register);
                finish();
            }
        },splash_timeout);
    }
}
