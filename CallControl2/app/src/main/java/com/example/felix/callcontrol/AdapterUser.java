package com.example.felix.callcontrol;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import static android.support.v7.recyclerview.R.styleable.RecyclerView;

/**
 * Created by Felix on 10/22/2016.
 */
public class AdapterUser extends android.support.v7.widget.RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    List<DataUser> data= Collections.emptyList();
    DataUser current;
    int currentPos=0;

    // create constructor to initialize context and data sent from MainActivity
    public AdapterUser(Context context, List<DataUser> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    // Inflate the layout when ViewHolder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.container_user, parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in RecyclerView to bind data and assign values from list
        MyHolder myHolder= (MyHolder) holder;
        DataUser current=data.get(position);
        myHolder.textUserName.setText("Name: "+current.userName);
        myHolder.textUserNumber.setText("Number: " + current.userNumber);
        myHolder.textState.setText("State: " + current.State);
        myHolder.textCity.setText("City " + current.City);
        // myHolder.textPrice.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));

    }


    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView  textUserName;
        TextView textUserNumber;
        TextView textState;
        TextView textCity;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            textUserName= (TextView) itemView.findViewById(R.id.textUserName);
            textUserNumber = (TextView) itemView.findViewById(R.id.textUserNumber);
            textState = (TextView) itemView.findViewById(R.id.textState);
            textCity = (TextView) itemView.findViewById(R.id.textCity);
            itemView.setOnClickListener(this);
        }

        // Click event for all items
        @Override
        public void onClick(View v) {

            Toast.makeText(context, "You clicked an item", Toast.LENGTH_SHORT).show();

        }



    }
}
