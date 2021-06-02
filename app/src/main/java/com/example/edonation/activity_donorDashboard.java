package com.example.edonation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.edonation.adapterandviewholders.donorAdapter;
import com.example.edonation.adapterandviewholders.organisationAdapter;
import com.example.edonation.pojoclasses.Donor;
import com.example.edonation.pojoclasses.Organisation;
import com.example.edonation.utils.ExtraUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class activity_donorDashboard extends Fragment {
    List<Donor> fetchData;
    RecyclerView recyclerView;
    com.example.edonation.adapterandviewholders.donorAdapter donorAdapter;
    DatabaseReference databaseReference;

    String email;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null)
            email = getArguments().getString(ExtraUtils.EXTRA_EMAIL); // Passing Data

        Log.i("EmailPassCheck","Email: "+email);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_home_donorlist_dashboard, container,false);
        recyclerView=view.findViewById(R.id.donorRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        fetchData=new ArrayList<>();

        databaseReference= FirebaseDatabase.getInstance().getReference("donorDetails");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ss:snapshot.getChildren())
                {
                    Donor data=ss.getValue(Donor.class);
                    fetchData.add(data);

                }
                donorAdapter=new donorAdapter( fetchData);
                recyclerView.setAdapter(donorAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
       return view;
    }
    }

