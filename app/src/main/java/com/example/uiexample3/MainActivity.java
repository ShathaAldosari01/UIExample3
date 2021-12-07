package com.example.uiexample3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.lang.annotation.Native;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout taskBtn;
    private RelativeLayout reminderBtn;

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.taskBtn:
                goToTasksActivity();
                break;
            case R.id.reminderBtn:
                goToRemindersActivity();
                break;
        }
    }

    public void goToTasksActivity(){
        Intent intent = new Intent(this, TasksActivity.class);
        startActivity(intent);
    }

    public void goToRemindersActivity(){
        Intent intent = new Intent(this, RemindersActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskBtn = findViewById(R.id.taskBtn);
        taskBtn.setOnClickListener(this);

        reminderBtn= findViewById(R.id.reminderBtn);
        reminderBtn.setOnClickListener(this);

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu){
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.main_menu, menu);
//        return true;
//    }
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item){
//        switch (item.getItemId()){
//            case R.id.settings_menu:
//                Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show();
//                return true;
//            default:
//                return super.onContextItemSelected(item);
//        }
//
//    }

}