/*
package com.example.edonation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edonation.adapterandviewholders.donorAdapter;
import com.example.edonation.pojoclasses.Donor;
import com.example.edonation.pojoclasses.Organisation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class activity_home_donor extends Fragment {
    List<Donor> fetchData;
    RecyclerView recyclerView;
    com.example.edonation.adapterandviewholders.donorAdapter donorAdapter;
    DatabaseReference databaseDonor, databaseOrganisation;
    FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    String databaseOrganisationName;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        View view= inflater.inflate(R.layout.activity_home_donorlist_dashboard,container, false);

        //email=user.getEmail();
        recyclerView=view.findViewById(R.id.homeRecyclerView);
        //recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext() ));
        user = firebaseAuth.getCurrentUser();

        databaseOrganisation=FirebaseDatabase.getInstance().getReference("organisationDetails");

        databaseDonor= FirebaseDatabase.getInstance().getReference("donorDetails");
        insertInforNav();

        databaseDonor.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ss:snapshot.getChildren())
                {
                    Donor data=ss.getValue(Donor.class);
                    int status;
                    fetchData.add(data);
                    try {
                        String orgName=data.getOrganisationName();
                        if(orgName.equals(databaseOrganisationName)){
                            fetchData.add(data);
                            donorAdapter=new donorAdapter(fetchData);
                            recyclerView.setAdapter(donorAdapter);
                        }

                    }catch(Exception e){

                    }

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
    private void initRecyclerView(){
        fetchData=new ArrayList<>();
        DatabaseReference databaseDonor=FirebaseDatabase.getInstance().getReference("donorDetails");
        databaseDonor.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void insertInforNav(){
        final String email;
        try{
            databaseOrganisation.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot ss:snapshot.getChildren())
                    {
                        Organisation data=ss.getValue(Organisation.class);
                        String emailFromDB="a";

                        databaseOrganisationName=data.getFullName();



                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }catch(Exception e){

        }
    }
}
*/
