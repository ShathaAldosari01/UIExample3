package com.example.uiexample3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TasksListAdpter extends RecyclerView.Adapter<TasksListAdpter.MyViewHolder> {

    private Context context;
    private ArrayList id, name,intl, ch;
//    int position;

    TasksListAdpter(Context co, ArrayList i, ArrayList n, ArrayList in, ArrayList c ){
        context = co;
        id = i;
        intl =in;
        ch =c;
        name =n;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.tasks_items, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        this.position = position;
        int p = position;
        holder.tasName.setText(String.valueOf(name.get(position)));
        holder.chip.setText(String.valueOf(intl.get(position)));
        if(String.valueOf(ch.get(position)).equals("1"))
            holder.cb.setChecked(true);
        else
            holder.cb.setChecked(false);
        holder.cb.setId(Integer.parseInt(String.valueOf(id.get(position))));
//        holder.cb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String ii = String.valueOf(id.get(p));
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        CheckBox cb;
        TextView dt, tasName;
        View line;
        RelativeLayout taskInfoDiv;
        com.google.android.material.chip.Chip chip;
        RelativeLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cb = itemView.findViewById(R.id.taskId);
            dt = itemView.findViewById(R.id.deleteTask);
            line = itemView.findViewById(R.id.line);
            taskInfoDiv = itemView.findViewById(R.id.taskInfoDiv);
            tasName = itemView.findViewById(R.id.tasName);
            chip = itemView.findViewById(R.id.chip);
            mainLayout= itemView.findViewById(R.id.mainLayout);
        }
    }
}
