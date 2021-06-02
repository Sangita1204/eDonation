package com.example.edonation.adapterandviewholders;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edonation.R;
import com.example.edonation.activity_mainDashboard;
import com.example.edonation.activity_organisation_detail;
import com.example.edonation.pojoclasses.CurrentlyLooking;
import com.example.edonation.pojoclasses.Organisation;

import java.util.List;

public class organisationAdapter extends RecyclerView.Adapter {
    List<Organisation> fetchDataList;
    Boolean books,clothes, stationery, food;
    String finalNeed;
    Context context;
    RelativeLayout relativeLayout;
    CurrentlyLooking currentlyLooking;

    public organisationAdapter( Context context, List<Organisation> fetchDataList) {
        this.fetchDataList = fetchDataList;
        this.context=context;

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.adapter_recycler_view_organisationlist, parent, false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String foodString;
        String clothesString;
        String booksString;
        String stationeryString;
        ViewHolder viewHolder=(ViewHolder)holder;
        Organisation fetchData=fetchDataList.get(position);
        viewHolder.orgName.setText(fetchData.getFullName());
        viewHolder.location.setText(fetchData.getLocation());

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
            viewHolder.needText.setText("Currently looking \n" +finalNeed);
           viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("name", fetchData.getFullName());
                    Intent intent=new Intent(context, activity_organisation_detail.class);
                    intent.putExtra("organisationName", fetchData.getFullName());
                    intent.putExtra("organisationLocation", fetchData.getLocation());
                    intent.putExtra("organisationEmail", fetchData.getEmail());
                    intent.putExtra("currentRequirement",finalNeed);

                    intent.putExtra("description", fetchData.getDescription());
                    intent.putExtra("website", fetchData.getWebsite());
                    intent.putExtra("phone", fetchData.getPhoneNo());
                    context.startActivity(intent);
                }
            });


    }

    @Override
    public int getItemCount() {
        return fetchDataList.size();
    }

    public class  ViewHolder extends  RecyclerView.ViewHolder{
        public View relativeLayout;
        TextView orgName, location, needText;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orgName=itemView.findViewById(R.id.orgName);
            location=itemView.findViewById(R.id.location);
            needText=itemView.findViewById(R.id.needTxt);
            relativeLayout =itemView.findViewById(R.id.relativeLayout);


        }
    }
}
