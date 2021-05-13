package com.example.edonation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.edonation.adapterandviewholders.organisationAdapter;
import com.example.edonation.pojoclasses.Organisation;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class activity_mainDashboard extends AppCompatActivity
{
    List<Organisation> fetchdata;
    RecyclerView recyclerView;
    organisationAdapter organisationAdapter;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maindashboard);
        recyclerView=findViewById(R.id.recyclerviewOrganisationList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fetchdata=new ArrayList<>();

        databaseReference= FirebaseDatabase.getInstance().getReference("organisationDetails");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ss:snapshot.getChildren())
                {
                    Organisation data=ss.getValue(Organisation.class);
                    fetchdata.add(data);

                }
                organisationAdapter=new organisationAdapter(activity_mainDashboard.this, fetchdata);
                recyclerView.setAdapter(organisationAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}