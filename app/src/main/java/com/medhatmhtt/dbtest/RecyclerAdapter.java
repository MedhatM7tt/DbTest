package com.medhatmhtt.dbtest;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by SM on 2/18/2018.
 */

public class RecyclerAdapter  extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{
    ArrayList<MovieAward> arrayList= new ArrayList<>();
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_xml,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return  myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.Name.setText(arrayList.get(position).getName());
        holder.ID.setText(""+arrayList.get(position).getID());
        holder.Type.setText(arrayList.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public RecyclerAdapter(ArrayList<MovieAward> arrayList) {
        this.arrayList = arrayList;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView ID,Name,Type;
        MyViewHolder(View itemView){
            super(itemView);
            Name=(TextView)itemView.findViewById(R.id.AwardName);
            ID=(TextView)itemView.findViewById(R.id.AwardID);
            Type=(TextView)itemView.findViewById(R.id.AwardType);
        }
    }
}
