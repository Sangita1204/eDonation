package com.example.edonation.adapterandviewholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edonation.R;
import com.example.edonation.pojoclasses.Organisation;

import java.util.List;

public class organisationAdapter extends RecyclerView.Adapter {
    List<Organisation> fetchDataList;
    String books,clothes, stationery, food;
    String finalNeed;


    public organisationAdapter(List<Organisation> fetchDataList) {
        this.fetchDataList = fetchDataList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_recycler_view_organisationlist, parent, false);
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

    }

    @Override
    public int getItemCount() {
        return fetchDataList.size();
    }

    public class  ViewHolder extends  RecyclerView.ViewHolder{
        TextView orgName, location, needText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orgName=itemView.findViewById(R.id.orgName);
            location=itemView.findViewById(R.id.location);
            needText=itemView.findViewById(R.id.needTxt);
        }
    }
}
