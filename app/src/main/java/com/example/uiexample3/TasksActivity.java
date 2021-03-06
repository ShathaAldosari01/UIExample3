package com.example.uiexample3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TasksActivity extends AppCompatActivity {//implements View.OnClickListener, CompoundButton.OnCheckedChangeListener{

    /*Delete msg |S1*/
    AlertDialog alertDialog;
    AlertDialog.Builder builder;
    /*End of Delete msg |S1*/

    private FloatingActionButton fab;
    private RecyclerView taskTodo;//contactRecView

//    private CheckBox checkBox;
//    private TextView deleteT;
//    private TaskList taskList;
    /*cheack and delete*/
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.deleteTask:
//                Toast.makeText(this, "Tast Deleted!", Toast.LENGTH_SHORT).show();
//                break;
//        }
//    }
//
//    @Override
//    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//        switch (compoundButton.getId()){
//            case R.id.taskId:
//                if(b)
//                    Toast.makeText(this, "Will Done!", Toast.LENGTH_SHORT).show();
//        }
//
//    }
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
//        if (task !=null)
//        Toast.makeText(TasksActivity.this, "task is "+task.getName()+",  "+task.getCheacked()+", "+task.getId()+", "+task.getIntensityLevel(), Toast.LENGTH_LONG).show();
        taskTodo = findViewById(R.id.tasksTodo);

        /*tasks | S2*/
        d= new db(TasksActivity.this);
        id= new ArrayList<>();
        name= new ArrayList<>();
        intLevel= new ArrayList<>();
        ch = new ArrayList<>();

        storDataInArray();

        /*tasks | S6*/
        tasksListAdpter = new TasksListAdpter(TasksActivity.this,id, name, intLevel,ch);
        taskTodo.setAdapter(tasksListAdpter);
        taskTodo.setLayoutManager(new LinearLayoutManager(TasksActivity.this));

        /*tasks list*/
//        if(taskList==null){
//            taskList = new TaskList();
//        }
        /*cheacking */
//        checkBox = findViewById(R.id.taskId);
//        checkBox.setOnCheckedChangeListener(this);
//
//        deleteT = findViewById(R.id.deleteTask);
//        deleteT.setOnClickListener(this);



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
    /*cheak */
    public void cheakMe(View view){
        long id = view.getId();

//        Toast.makeText(this, "Will Done!"+id,Toast.LENGTH_SHORT).show();
        String che =d.changeCheack(id);
//        Toast.makeText(this, "Will Done!"+id+che,Toast.LENGTH_SHORT).show();

    }
    public void deleteMe(View view){
        /*Delete msg |S2*/
        long id = view.getId();
        //delete 2.1
        builder = new AlertDialog.Builder(TasksActivity.this);//change TasksActivity to your page

        builder.setTitle("Are you sure you want to delete the task?");
//        builder.setSingleChoiceItems(item, -1, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//
//            }
//        });

        builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                d.deleteTask(id-1);
                finish();
                startActivity(getIntent());
                Toast.makeText(TasksActivity.this, "the Task has been deleted!", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(TasksActivity.this, "the deletion was canceled!", Toast.LENGTH_SHORT).show();
            }
        });

        // 2.2
        alertDialog = builder.create();
        alertDialog.show();
        /*End of Delete msg |S2*/

//        Toast.makeText(this, "Delete!", Toast.LENGTH_SHORT).show();

    }
}