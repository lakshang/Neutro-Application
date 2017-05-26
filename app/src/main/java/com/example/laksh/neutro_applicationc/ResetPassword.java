package com.example.laksh.neutro_applicationc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {
Button btnResetPassword;
EditText txtREmail;
    private ProgressDialog pd;
    private FirebaseAuth firebaseAuthentication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        firebaseAuthentication = FirebaseAuth.getInstance();
        btnResetPassword = (Button) findViewById(R.id.btnReset);
        txtREmail = (EditText) findViewById(R.id.txtREmail);
        pd = new ProgressDialog(this);

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });
    }
    private void resetPassword(){
        String email = txtREmail.getText().toString().trim();
        pd.setMessage(" Resetting Password....");
        pd.show();
        firebaseAuthentication.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(ResetPassword.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(ResetPassword.this, LoginActivity.class));
                    pd.dismiss();
                } else {
                    Toast.makeText(ResetPassword.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
            }
        });
    }

}
