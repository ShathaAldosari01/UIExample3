package com.example.uiexample3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class TaskFormActivity extends AppCompatActivity {

    private Button addTask;
    private EditText taskName;
    private Spinner taskIntensityLevel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_form);

        taskName = findViewById(R.id.taskName);
        addTask= findViewById(R.id.addTask);
        taskIntensityLevel = findViewById(R.id.taskIntensityLevel);
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tname = taskName.getText().toString(),
                        ti = taskIntensityLevel.getSelectedItem().toString();
                if(!(tname.equals(""))){
                    Task task = new Task(tname,ti);
                    Toast.makeText(TaskFormActivity.this, "task has been added with name= "+tname+ "and i= "+ti, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TaskFormActivity.this, TasksActivity.class);
                    intent.putExtra("task",task );
                    startActivity(intent);
                }else {
                    Toast.makeText(TaskFormActivity.this, "you need to full ALL the form to add the task", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}