package com.example.uiexample3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class TaskFormActivity extends AppCompatActivity {

    private Button addTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_form);

        addTask= findViewById(R.id.addTask);
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(TaskFormActivity.this, "task has been added", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(TaskFormActivity.this, TasksActivity.class);
                startActivity(intent);
            }
        });
    }
}