package com.example.edonation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.edonation.utils.ExtraUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class activity_login extends AppCompatActivity {

    TextView registerHere, donorLink;
    EditText loginFullName, loginPassword;
    Button loginButton;
    ProgressBar progressBar;

    private Boolean validateOrganisationEmail(){
        String val=loginFullName.getText().toString();

        if (val.isEmpty()){
            loginFullName.setError("Username cannot be empty");
            return  false;
        }else{
            loginFullName.setError(null);

            return true;
        }
    }

    private Boolean validatePassword(){
        String val=loginPassword.getText().toString();
        if (val.isEmpty()){
            loginPassword.setError("Password cannot be empty");
            return  false;
        }else{
            loginPassword.setError(null);

            return true;
        }
    }
   /* public void loginOrganisation(View view){
        if(!validateOrganisationEmail()|| !validatePassword()){
            return;
        }else{
            isOrganisation();
        }
    }*/

    private void isOrganisation(){
        try{
            String organisationEnteredFullName=loginFullName.getText().toString().trim();
            String organisationEnteredPassword=loginPassword.getText().toString().trim();


            DatabaseReference reference= FirebaseDatabase.getInstance().getReference("organisationDetails");
            Query checkOrganisation= reference.orderByChild("fullName").equalTo(organisationEnteredFullName);

            checkOrganisation.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot datasnapshot) {

                    if(datasnapshot.exists()){
                        loginFullName.setError(null);
                        /*System.out.println("pa"+ organisationEnteredFullName);
                        String organisationFullname=datasnapshot.child(organisationEnteredFullName).child("ABN").getValue(String.class);
                        Log.d("nhujaw",organisationFullname);
                        System.out.println("abn"+ organisationFullname);*/
                        String ad=organisationEnteredFullName;
                        String passwordFromDB= datasnapshot.child(organisationEnteredFullName).child("password").getValue(String.class);
                        System.out.println("perspective"+ passwordFromDB);
                        if(passwordFromDB.equals(organisationEnteredPassword)){
                            loginPassword.setError(null);
                            // Value null aairako cha
                            //String email= datasnapshot.child(organisationEnteredFullName).child("registerFullName").getValue(String.class);
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "Login Successfully",
                                    Toast.LENGTH_LONG);
                            toast.show();
                            /*progressBar.setVisibility(View.VISIBLE);*/
                            Intent intent=new Intent(getApplicationContext(), activity_organisationDashboard.class);
                            intent.putExtra(ExtraUtils.EXTRA_EMAIL, organisationEnteredFullName); // Passing Data
                            startActivity(intent);
                        }else{
                            loginPassword.setError("Wrong Password");
                            loginPassword.requestFocus();
                        }
                    }
                    else{
                        loginFullName.setError("No such organisation exist");
                        loginFullName.requestFocus();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }catch (Exception e){
            System.out.println("exception" +e);
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginFullName=findViewById(R.id.loginFullName);
        loginPassword=findViewById(R.id.loginPassword);
        registerHere=findViewById(R.id.loginRegisterLink);
        donorLink=findViewById(R.id.loginDonorLink);
        loginButton=findViewById(R.id.loginButton);
        progressBar=findViewById(R.id.progressBar);

        registerHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(getApplicationContext(), activity_register.class);
                startActivity(intent);

            }

        });
        donorLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), activity_mainDashboard.class);
                startActivity(intent);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateOrganisationEmail()|| !validatePassword()){
                    return;
                }else{
                    isOrganisation();
                }

            }
        });
    }

}