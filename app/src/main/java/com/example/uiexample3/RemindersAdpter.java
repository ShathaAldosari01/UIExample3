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
//RemindersAdpter | TasksListAdpter
public class RemindersAdpter extends RecyclerView.Adapter<RemindersAdpter.MyViewHolder> {

    private Context context;
    private ArrayList id, name,intl, ch, date;

    RemindersAdpter(Context co, ArrayList i, ArrayList n, ArrayList in, ArrayList c, ArrayList da ){
        context = co;
        id = i;
        intl =in;
        ch =c;
        name =n;
        date = da;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.reminders_items, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       holder.tasName.setText(String.valueOf(name.get(position)));
       holder.chip.setText(String.valueOf(intl.get(position)));
       if(String.valueOf(ch.get(position)).equals("1"))
            holder.cb.setChecked(true);
       else
           holder.cb.setChecked(false);
        holder.dateTim.setText(String.valueOf(date.get(position)));
        holder.cb.setId(Integer.parseInt(String.valueOf(id.get(position))));


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
        com.google.android.material.chip.Chip dateTim;
        RelativeLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cb = itemView.findViewById(R.id.taskId);
            dt = itemView.findViewById(R.id.deleteTask);
            line = itemView.findViewById(R.id.line);
            taskInfoDiv = itemView.findViewById(R.id.taskInfoDiv);
            tasName = itemView.findViewById(R.id.tasName);
            chip = itemView.findViewById(R.id.level);
            dateTim = itemView.findViewById(R.id.date);
            mainLayout= itemView.findViewById(R.id.mainLayout);

        }
    }
}

