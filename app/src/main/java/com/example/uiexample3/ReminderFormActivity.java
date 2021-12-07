package com.example.uiexample3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ReminderFormActivity extends AppCompatActivity {

    private Button addReminder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_form);
        addReminder = findViewById(R.id.addReminder);
        addReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ReminderFormActivity.this, "reminder was added", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ReminderFormActivity.this, RemindersActivity.class);
                startActivity(intent);
            }
        });
    }
}