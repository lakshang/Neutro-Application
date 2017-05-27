package com.example.laksh.neutro_applicationc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class ProfileActivity extends AppCompatActivity {
private ImageView ivProfile;
private TextView viewUser;
private Button btnUpdate;
private EditText txtBabyName, txtHeight, txtWeight, txtMonth;
private FirebaseAuth firebaseAuth;
private ProgressDialog progressDialog;
private FirebaseUser User;
private DatabaseReference dbReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();
        dbReference = FirebaseDatabase.getInstance().getReference("Details");
        if (firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        displayData();

        ivProfile = (ImageView) findViewById(R.id.ivProfile);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);

        txtBabyName = (EditText) findViewById(R.id.txtBabysName);
        txtHeight = (EditText) findViewById(R.id.txtHeight);
        txtWeight = (EditText) findViewById(R.id.txtWieght);
        txtMonth =(EditText) findViewById(R.id.txtMonth);
        viewUser = (TextView) findViewById(R.id.viewUser) ;
        progressDialog = new ProgressDialog(this);


        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, ProfileActivity.class));
                finish();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //Update data action
              progressDialog.setMessage("Updating Information...");
              progressDialog.show();
              saveData();
              Toast.makeText(ProfileActivity.this, " Data Has been Saved ! ", Toast.LENGTH_SHORT).show();
              finish();
              startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

    }
    private void saveData(){
        try {
            String babyName = txtBabyName.getText().toString().trim();
            String height = (txtHeight.getText().toString());
            String weight =((txtWeight.getText().toString()));
            String month = ((txtMonth.getText().toString()));

            if (TextUtils.isEmpty(babyName)){
                Toast.makeText(this, " Please enter Baby Name ", Toast.LENGTH_LONG).show();
                //stop the function
                return;
            }
            if (TextUtils.isEmpty(height)){
                Toast.makeText(this, " Please enter Baby Height ", Toast.LENGTH_LONG).show();
                //stop the function
                return;
            }
            if (TextUtils.isEmpty(weight)){
                Toast.makeText(this, " Please enter Baby Weight ", Toast.LENGTH_LONG).show();
                //stop the function
                return;
            }
            if (TextUtils.isEmpty(month)){
                Toast.makeText(this, " Please enter Baby Age ", Toast.LENGTH_LONG).show();
                //stop the function
                return;
            }

            FirebaseUser id = firebaseAuth.getCurrentUser();
            PersonalDetails details = new PersonalDetails(babyName,height,weight,month);
            dbReference.child(id.getUid()).setValue(details);

        }catch (InputMismatchException e){
            Toast.makeText(ProfileActivity.this, " Please check the info entered! ", Toast.LENGTH_SHORT).show();
            return;
        }

    }
    private void displayData(){
        final ArrayList<String> dataArray = new ArrayList<String>();
    dbReference.addChildEventListener(new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            String value = dataSnapshot.getValue().toString().trim();
            //txtBabyName.setText(value);

            for (String retrieve : value.split("[{}=,]")){
               dataArray.add(retrieve);
                System.out.println(dataArray);
            }
            txtBabyName.setText(dataArray.get(2).toString());
            txtHeight.setText(dataArray.get(4).toString());
            txtWeight.setText(dataArray.get(6).toString());
            txtMonth.setText(dataArray.get(8).toString());
            viewUser.setText(dataArray.get(2).toString());
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });
    }
}
