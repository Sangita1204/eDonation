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
import android.widget.Toast;

import com.example.edonation.pojoclasses.CurrentlyLooking;
import com.example.edonation.pojoclasses.Organisation;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_register extends AppCompatActivity {
   EditText registerFullName, registerEmail, registerPassword, registerLocation,
    registerPhoneNo, registerWebsite, registerABN, registerDescription;
    Button registerButton;
    CheckBox checkboxStationery, checkboxClothes,
    checkboxBooks, checkboxFood;
    private boolean foodBoolean, clothesBoolean, stationeryBoolean, booksBoolean;

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
        registerDescription=findViewById(R.id.registerDescription);
        checkboxBooks=findViewById(R.id.checkboxBooks);
        checkboxClothes=findViewById(R.id.checkboxClothes);
        checkboxFood=findViewById(R.id.checkboxFood);
        checkboxStationery=findViewById(R.id.checkboxStationery);

        String ckClothes="Clothes";
        String ckBooks="Books";
        String ckStationery="Stationery";
        String ckFood="Food";

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
                String description=registerDescription.getText().toString();
                if (checkboxFood.isChecked()) {
                    foodBoolean = true;
                    //= "," + "Stationery"Log.i("food", currentlyLooking);
                }

                if (checkboxBooks.isChecked()) {
                    booksBoolean = true;
                    //Log.i("clothes", currentlyLooking);
                }

                if (checkboxClothes.isChecked()) {
                    clothesBoolean = true;
                }

                if (checkboxStationery.isChecked()) {
                    stationeryBoolean = true;
                }

                CurrentlyLooking currentlyLooking = new CurrentlyLooking(foodBoolean,
                        clothesBoolean, booksBoolean, stationeryBoolean);
                Organisation organisationData=new Organisation(fullName, email, password, location, website, ABN, phoneNo, description, currentlyLooking);
              Toast toast = Toast.makeText(getApplicationContext(),
                                    "Registered Successfully",
                                    Toast.LENGTH_LONG);
                toast.show();
//                            progressBar.setVisibility(View.VISIBLE);
                reference.child(fullName).setValue(organisationData);
                Intent intent=new Intent(getApplicationContext(),activity_login.class);
                startActivity(intent);
                


            }
        });



    }
}