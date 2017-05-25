package com.example.laksh.neutro_applicationc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnRegisterS;
    private EditText txtEmail, txtPassword;
    private TextView viewLogin;
    private ProgressDialog progressD;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
        progressD = new ProgressDialog(this);
        btnRegisterS = (Button) findViewById(R.id.btnRegister);
        txtEmail = (EditText) findViewById(R.id.txtEmailAddress);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        viewLogin = (TextView) findViewById(R.id.viewLogin);

        btnRegisterS.setOnClickListener(this);
        viewLogin.setOnClickListener(this);


    }
    private void registerUser() {
        String uEmail = txtEmail.getText().toString().trim();
        String uPassword = txtPassword.getText().toString().trim();

        if (TextUtils.isEmpty(uEmail)) {
            Toast.makeText(this, " Please enter Email ", Toast.LENGTH_LONG).show();
            //stop the function
            return;
        }
        if (TextUtils.isEmpty(uPassword)) {
            Toast.makeText(this, " Please enter Password ", Toast.LENGTH_LONG).show();
            //stop the function
            return;
        }
        progressD.setMessage(" Registering User....");
        progressD.show();
        firebaseAuth.createUserWithEmailAndPassword(uEmail, uPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //user successfully registerd
                            //profile activity
                            finish();
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            progressD.dismiss();
//                           Toast.makeText(MainActivity.this, " Registration Successful ! ", Toast.LENGTH_SHORT).show();
                        } else {
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if (v == btnRegisterS) {
            registerUser();

        }
        if (v == viewLogin) {
            //sign in
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
