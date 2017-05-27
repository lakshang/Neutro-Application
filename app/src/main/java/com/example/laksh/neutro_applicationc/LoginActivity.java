package com.example.laksh.neutro_applicationc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
private Button btnLogin;
private EditText txtEmail, txtPassword;
private TextView tvRegister, viewResetPassword;
private ProgressDialog pd;
private FirebaseAuth firebaseAuthentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuthentication = FirebaseAuth.getInstance();
        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtEmail = (EditText) findViewById(R.id.txtLEmail);
        txtPassword = (EditText) findViewById(R.id.txtLPassword);
        tvRegister = (TextView) findViewById(R.id.tvRegister);
        viewResetPassword = (TextView) findViewById(R.id.viewReset) ;

        pd = new ProgressDialog(this);
        btnLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        viewResetPassword.setOnClickListener(this);

        if(firebaseAuthentication.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }

    }
    private void userLogin(){
        FirebaseUser user = firebaseAuthentication.getCurrentUser();
        String email = txtEmail.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, " Please enter Email ", Toast.LENGTH_LONG).show();
            //stop the function
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, " Please enter Password ", Toast.LENGTH_LONG).show();
            //stop the function
            return;
        }
        pd.setMessage(" Sigining....");
        pd.show();
        firebaseAuthentication.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()){
                            //start profile
                            finish();
                            pd.dismiss();
                            Toast.makeText(LoginActivity.this, " Invalid Email/ Password ! ", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                        }else{
                            pd.dismiss();
                            finish();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if (v == btnLogin){
            userLogin();
        }
        if (v == tvRegister){
            startActivity(new Intent(this, RegisterActivity.class));
            finish();
        }
        if (v == viewResetPassword){
            startActivity(new Intent(this, ResetPassword.class));
            finish();
        }
    }
}
