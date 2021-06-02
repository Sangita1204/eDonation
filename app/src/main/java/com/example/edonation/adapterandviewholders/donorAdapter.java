package com.example.edonation.adapterandviewholders;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edonation.R;
import com.example.edonation.activity_donorDashboard;
import com.example.edonation.activity_organisation_detail;
import com.example.edonation.pojoclasses.CurrentlyLooking;
import com.example.edonation.pojoclasses.Donor;
import com.example.edonation.pojoclasses.Organisation;

import java.util.List;

public class donorAdapter extends RecyclerView.Adapter{
    List<Donor> fetchDataList;
    Boolean books,clothes, stationery, food;
    String finalNeed;
    Context context;
    RelativeLayout relativeLayout;
    CurrentlyLooking currentlyLooking;

    public donorAdapter( List<Donor> fetchDataList) {
        this.fetchDataList = fetchDataList;


    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_recycler_view_donor, parent, false);
        donorAdapter.ViewHolder viewHolder=new donorAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String foodString;
        String clothesString;
        String booksString;
        String stationeryString;
        donorAdapter.ViewHolder viewHolder=(donorAdapter.ViewHolder)holder;
        Donor fetchData=fetchDataList.get(position);
        viewHolder.name.setText(fetchData.getFullNameDonor());
        viewHolder.location.setText(fetchData.getLocationDonor());
        viewHolder.email.setText(fetchData.getEmailDonor());
        viewHolder.phoneNO.setText(fetchData.getPhoneNoDonor().toString());

        boolean clothes = fetchData.getCurrentlyLooking().isClothes();
        boolean books = fetchData.getCurrentlyLooking().isBooks();
        boolean stationery = fetchData.getCurrentlyLooking().isStationery();
        boolean food = fetchData.getCurrentlyLooking().isFoods();

        if(books==true){
            booksString="Book\n";

        }else{
            booksString="";
        }if(clothes==true){
            clothesString="Clothes\n";
        }else{
            clothesString="";
        }if(stationery==true){
            stationeryString="Stationery\n";
        }else{
            stationeryString="";
        }if(food==true){
            foodString="Food\n";
        }else{
            foodString="";
        }
        finalNeed=foodString+clothesString+booksString+stationeryString;
        viewHolder.donatedItems.setText("Donated Items \n" +finalNeed);
        viewHolder.organisationName.setText(fetchData.getOrganisationName());



    }

    @Override
    public int getItemCount() {
        return fetchDataList.size();
    }

    public class  ViewHolder extends  RecyclerView.ViewHolder{

        TextView name, location, email, donatedItems, organisationName, phoneNO;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.donorName);
            location=itemView.findViewById(R.id.location);
            phoneNO=itemView.findViewById(R.id.phone);
            donatedItems=itemView.findViewById(R.id.donatedItems);
            organisationName=itemView.findViewById(R.id.organisation_name);
            email=itemView.findViewById(R.id.email);



        }
    }
}
