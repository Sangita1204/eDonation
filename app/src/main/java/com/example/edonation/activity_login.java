package com.example.edonation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class activity_login extends AppCompatActivity {

    TextView registerHere;
    EditText loginEmail, loginPassword;
    Button loginButton;

    private Boolean validateOrganisationEmail(){
        String val=loginEmail.getText().toString();
        Log.d("hello", val);
        if (val.isEmpty()){
            loginEmail.setError("Email cannot be empty");
            return  false;
        }else{
            loginEmail.setError(null);

            return true;
        }
    }

    private Boolean validatePassword(){
        String val=loginPassword.getText().toString();
        if (val.isEmpty()){
            loginPassword.setError("Email cannot be empty");
            return  false;
        }else{
            loginPassword.setError(null);

            return true;
        }
    }
    public void loginOrganisation(View view){
        if(!validateOrganisationEmail()|| !validatePassword()){
            return;
        }else{
            isOrganisation();
        }
    }

    private void isOrganisation(){
        String organisationEnteredEmail=loginEmail.getText().toString().trim();
        String organisationEnteredPassword=loginPassword.getText().toString().trim();

        System.out.println("lo"+ organisationEnteredPassword);
        //DatabaseReference reference= FirebaseDatabase.getInstance().getReference("organisationDetails");
        Query checkOrganisation= FirebaseDatabase.getInstance().getReference("organisationDetails").orderByChild("registerEmail").equalTo(organisationEnteredEmail);

         checkOrganisation.addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot datasnapshot) {

                 if(datasnapshot.exists()){
                     loginEmail.setError(null);
                     System.out.println("pa");
                    String ad=organisationEnteredEmail;
                    Log.d("hellooooo",ad);
                     String passwordFromDB= datasnapshot.child(organisationEnteredEmail).child("registerPassword").getValue(String.class);
                     System.out.println("pa"+ passwordFromDB);
                     if(passwordFromDB.equals(organisationEnteredPassword)){
                         loginPassword.setError(null);
                         String email= datasnapshot.child(organisationEnteredEmail).child("registerEmail").getValue(String.class);
                         Intent intent=new Intent(getApplicationContext(), activity_register.class);
                         startActivity(intent);
                     }else{
                            loginPassword.setError("Wrong Password");
                            loginPassword.requestFocus();
                     }
                 }
                 else{
                     loginEmail.setError("No such organisation exist");
                     loginEmail.requestFocus();
                 }
             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginEmail=findViewById(R.id.loginEmail);
        loginPassword=findViewById(R.id.loginPassword);
        registerHere=findViewById(R.id.loginRegisterLink);
        loginButton=findViewById(R.id.loginButton);

        registerHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), activity_register.class);
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