package com.example.edonation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.edonation.pojoclasses.CurrentlyLooking;
import com.example.edonation.pojoclasses.Donor;
import com.example.edonation.utils.ExtraUtils;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_add_donor extends Fragment {
    EditText donorFullName, donorEmail, donorLocation, donorPhoneNo, donorDescription, organisationFullName;
    CheckBox checkboxStationery, checkboxClothes,
            checkboxBooks, checkboxFood;
    Button addDonorButton;
    private boolean foodBoolean, clothesBoolean, stationeryBoolean, booksBoolean;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view= inflater.inflate(R.layout.fragment_adddonor,container, false);
        donorFullName=(EditText) view.findViewById(R.id.donorFullName);
        donorEmail=(EditText) view.findViewById(R.id.donorEmail);
        donorLocation=(EditText) view.findViewById(R.id.donorLocation);
        donorPhoneNo=(EditText) view.findViewById(R.id.donorPhoneNo);
        organisationFullName=(EditText) view.findViewById(R.id.organisation_fullName);
        checkboxBooks=(CheckBox) view.findViewById(R.id.checkboxBooks);
        checkboxClothes=(CheckBox) view.findViewById(R.id.checkboxClothes);
        checkboxFood=(CheckBox) view.findViewById(R.id.checkboxFood);
        checkboxStationery= (CheckBox) view.findViewById(R.id.checkboxStationery);
        donorDescription=(EditText)view.findViewById(R.id.donorDescription);
        addDonorButton=(Button) view.findViewById(R.id.addDonorButton);
        String ckClothes="Clothes";
        String ckBooks="Books";
        String ckStationery="Stationery";
        String ckFood="Food";
        addDonorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode=FirebaseDatabase.getInstance();
                reference=rootNode.getReference("donorDetails");
                //GetAllValues
                String organisationName=organisationFullName.getText().toString();
                String fullNameDonor=donorFullName.getText().toString();
                String emailDonor=donorEmail.getText().toString();
                String locationDonor=donorLocation.getText().toString();
                Long phoneNoDonor= Long.parseLong(donorPhoneNo.getText().toString());
                String descriptionDonor= donorDescription.getText().toString();
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
                String donorId= reference.push().getKey();
                CurrentlyLooking currentlyLooking = new CurrentlyLooking(foodBoolean,
                        clothesBoolean, booksBoolean, stationeryBoolean);
                Donor donorData=new Donor(donorId,organisationName,fullNameDonor,emailDonor, locationDonor, phoneNoDonor, currentlyLooking, descriptionDonor);
                reference.child(donorId).setValue(donorData);
                Toast toast = Toast.makeText(getActivity(),
                        "Donor Added Successfully",
                        Toast.LENGTH_SHORT);

                toast.show();
            }
        });
                return view;
    }
    public void initComponents(){

    }
}
