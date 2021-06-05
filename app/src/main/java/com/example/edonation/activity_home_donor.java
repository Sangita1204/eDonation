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
import com.example.edonation.utils.ExtraUtils;
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
    String fullName;
    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null)
            fullName = getArguments().getString(ExtraUtils.EXTRA_EMAIL); // Passing Data

        Log.i("EmailPassCheckers","Email: "+fullName);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        View view= inflater.inflate(R.layout.activity_home_donorlist_dashboard,container, false);


        recyclerView=view.findViewById(R.id.homeRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext() ));


        databaseOrganisation=FirebaseDatabase.getInstance().getReference("organisationDetails");

        databaseDonor= FirebaseDatabase.getInstance().getReference("donorDetails");
        insertInforNav();

        databaseDonor.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ss:snapshot.getChildren())
                {
                    Donor datas=ss.getValue(Donor.class);
                    int status;
                    fetchData.add(datas);
                    try {
                        String orgName=datas.getOrganisationName();
                        if(orgName.equals(fullName)){
                            fetchData.add(datas);
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
                    if (snapshot.exists()) {
                        for (DataSnapshot ss : snapshot.getChildren()) {
                            Organisation data = ss.getValue(Organisation.class);
                            String fullNameFromDb = data.getFullName();


                            if (fullName.equals(fullNameFromDb)) {
                                String name = data.getFullName();
                                String emailID = data.getEmail();
                            /*organizationNameString=org.getOrgFullName();
                            organizationEmail.setText(emailID);
                            organizationName.setText(name);*/
                            }


                        }
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
