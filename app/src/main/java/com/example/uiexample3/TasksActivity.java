package com.example.uiexample3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TasksActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private RecyclerView taskTodo;//contactRecView
    /*tasks | S1*/
    db d ;
    ArrayList<String> id, name, intLevel, ch;
    /*tasks | S5*/
    TasksListAdpter tasksListAdpter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        fab = findViewById(R.id.taskAddBtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TasksActivity.this, TaskFormActivity.class);
                startActivity(intent);
            }
        });

        Task task = getIntent().getParcelableExtra("task");
        if (task !=null)
        Toast.makeText(TasksActivity.this, "task is "+task.getName()+",  "+task.getCheacked()+", "+task.getId()+", "+task.getIntensityLevel(), Toast.LENGTH_LONG).show();
        taskTodo = findViewById(R.id.tasksTodo);

        /*tasks | S2*/
        d= new db(TasksActivity.this);
        id= new ArrayList<>();
        name= new ArrayList<>();
        intLevel= new ArrayList<>();

        storDataInArray();

        /*tasks | S6*/
        tasksListAdpter = new TasksListAdpter(TasksActivity.this,id, name, intLevel,ch);
        taskTodo.setAdapter(tasksListAdpter);
        taskTodo.setLayoutManager(new LinearLayoutManager(TasksActivity.this));
    }
    /*tasks | S3*/
    public void storDataInArray(){

        Cursor cursor = d.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(TasksActivity.this, "No Tasks yet", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                intLevel.add(cursor.getString(2));
                ch.add(cursor.getString(3));
//                ch.add(0);
            }
        }
    }
}