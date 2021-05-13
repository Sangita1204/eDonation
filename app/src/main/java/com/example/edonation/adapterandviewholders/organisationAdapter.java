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
import com.example.edonation.pojoclasses.Organisation;

import java.util.List;

public class organisationAdapter extends RecyclerView.Adapter {
    List<Organisation> fetchDataList;
    String books,clothes, stationery, food;
    String finalNeed;
    Context context;
    RelativeLayout relativeLayout;

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
           ViewHolder viewHolder=(ViewHolder)holder;
            Organisation fetchData=fetchDataList.get(position);
            viewHolder.orgName.setText(fetchData.getFullName());
            viewHolder.location.setText(fetchData.getLocation());

            if(fetchData.getBooks()!=null){
                books=fetchData.getBooks();

            }else{
                books="";
            }if(fetchData.getClothes()!=null){
            clothes=fetchData.getClothes();
        }else{
                clothes="";
        }if(fetchData.getStationery()!=null){
            stationery=fetchData.getStationery();
        }else{
            stationery="";
        }if(fetchData.getFood()!=null){
                food=fetchData.getFood();
        }else{

        }
            finalNeed="Currently Looking \n"+books +"\n"+clothes+"\n"+stationery+"\n"+food;
            viewHolder.needText.setText(finalNeed);
           viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("name", fetchData.getFullName());
                    Intent intent=new Intent(context, activity_organisation_detail.class);
                    intent.putExtra("organisationName", fetchData.getFullName());
                    intent.putExtra("organisationLocation", fetchData.getLocation());
                    intent.putExtra("organisationEmail", fetchData.getEmail());
                   // intent.putExtra("currentDonation",currentlyLooking);

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
