package com.t.realfitness;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.t.realfitness.SQliteRoom.Exercise;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    List<Exercise>list;

    public RecyclerViewAdapter(List<Exercise> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view_exercise,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {

        holder.time.setText(list.get(position).getTime());
        holder.date.setText(list.get(position).getDate());
        holder.type.setText(list.get(position).getType());
        holder.workOutTime.setText(list.get(position).getWorkoutTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView time,date,type,workOutTime;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            time=itemView.findViewById(R.id.time);
            date=itemView.findViewById(R.id.date);
            type=itemView.findViewById(R.id.type);
            workOutTime=itemView.findViewById(R.id.workOutTime);

        }
    }
}
