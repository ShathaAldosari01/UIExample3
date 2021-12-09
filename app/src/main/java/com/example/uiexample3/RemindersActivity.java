package com.example.uiexample3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.database.Cursor;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class RemindersActivity extends AppCompatActivity {

    /*Delete msg |S1*/
    AlertDialog alertDialog;
    AlertDialog.Builder builder;
    /*End of Delete msg |S1*/

    private FloatingActionButton fab;
    private RecyclerView reminderTodo;//contactRecView
    /*tasks | S1*/
    db d ;
    ArrayList<String> id, name, intLevel, ch, datim;
    /*tasks | S5*/
    RemindersAdpter remindersAdpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);

        fab = findViewById(R.id.reminderAddBtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RemindersActivity.this, ReminderFormActivity.class);
                startActivity(intent);
            }
        });
        //don't know why
        Reminder reminder = getIntent().getParcelableExtra("reminder");
//        if (reminder !=null)
//            Toast.makeText(RemindersActivity.this, "reminder is "+reminder.getName()+",  "+reminder.getCheacked()+", "+reminder.getId()+", "+reminder.getIntensityLevel(), Toast.LENGTH_LONG).show();
        reminderTodo = findViewById(R.id.reminderTodo);

        /*tasks | S2*/
        d= new db(RemindersActivity.this);
        id= new ArrayList<>();
        name= new ArrayList<>();
        intLevel= new ArrayList<>();
        ch = new ArrayList<>();
        datim = new ArrayList<>();

        storDataInArray();

        /*tasks | S6*/
        remindersAdpter = new RemindersAdpter(RemindersActivity.this,id, name, intLevel, ch,datim);//,ch);
        reminderTodo.setAdapter(remindersAdpter);
        reminderTodo.setLayoutManager(new LinearLayoutManager(RemindersActivity.this));
    }
    /*tasks | S3*/
    public void storDataInArray(){

        Cursor cursor = d.readAllReminder();
        if(cursor.getCount() == 0){
            Toast.makeText(RemindersActivity.this, "No reminders yet", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                intLevel.add(cursor.getString(2));
                ch.add(cursor.getString(3));
                datim.add(cursor.getString(4));
//                ch.add(0);
            }
        }
    }

    /*cheak, delete*/
    public void cheakMe(View view){
        long id = view.getId();

//        Toast.makeText(this, "Will Done!"+id,Toast.LENGTH_SHORT).show();
        String che =d.changeCheackReminder(id);
//        Toast.makeText(this, "Will Done!"+id,Toast.LENGTH_SHORT).show();

    }
    public void deleteMe(View view){

        /*Delete msg |S2*/
        long id = view.getId();
        //delete 2.1
        builder = new AlertDialog.Builder(RemindersActivity.this);//change TasksActivity to your page

        builder.setTitle("Are you sure you want to delete the reminder?");

        builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                d.deleteReminder(id-1);
                finish();
                startActivity(getIntent());
                Toast.makeText(RemindersActivity.this, "The reminder has been deleted!", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(RemindersActivity.this, "Delete was canceled!", Toast.LENGTH_SHORT).show();
            }
        });

        // 2.2
        alertDialog = builder.create();
        alertDialog.show();
        /*End of Delete msg |S2*/

//        Toast.makeText(this, "Delete!", Toast.LENGTH_SHORT).show();

    }


}