package com.example.edonation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.edonation.pojoclasses.Organisation;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_register extends AppCompatActivity {
   EditText registerFullName, registerEmail, registerPassword, registerLocation,
    registerPhoneNo, registerWebsite, registerABN;
    Button registerButton;
    CheckBox checkboxStationery, checkboxClothes,
    checkboxBooks, checkboxFood;
    EditText registerDescription;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerFullName= findViewById(R.id.registerFullName);
        registerEmail=findViewById(R.id.registerEmail);
        registerPassword=findViewById(R.id.registerPassword);
        registerLocation=findViewById(R.id.registerLocation);
        registerPhoneNo=findViewById(R.id.registerPhoneno);
        registerWebsite=findViewById(R.id.registerWebsite);
        registerABN=findViewById(R.id.registerABN);
        registerButton=findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode= FirebaseDatabase. getInstance();
                reference= rootNode.getReference("organisationDetails");

                //Get all values
                String fullName= registerFullName.getText().toString();
                String email= registerEmail.getText().toString();
                String password= registerPassword.getText().toString();
                String location= registerFullName.getText().toString();
                long phoneNo= Long.parseLong(registerPhoneNo.getText().toString());
                String website= registerWebsite.getText().toString();
                String ABN= registerABN.getText().toString();
                Organisation organisationData=new Organisation(fullName, email, password, location, website, phoneNo, ABN);
                reference.child(email).setValue(organisationData);
                Intent intent=new Intent(getApplicationContext(),activity_login.class);
                startActivity(intent);
                


            }
        });



    }
}