package com.example.uiexample3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;

import android.widget.DatePicker;

import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ReminderFormActivity extends AppCompatActivity {


    private Button addReminder;
    private Spinner tasksSpinner;
    private EditText reminderName;
    EditText date_time_in;
    /*notification ~ s3 | 1 */
    private NotificationManagerCompat notificationManager;
    /*notification ~ s3 | end 1*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_form);

        /*Spinner*/
        tasksSpinner = findViewById(R.id.reminderIntensityLevel);

        ArrayList<String> intensityLevel = new ArrayList<>();

        intensityLevel.add("Low");
        intensityLevel.add("Moderate");
        intensityLevel.add("High");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                R.layout.spinner_item,
                intensityLevel
        );

        tasksSpinner.setAdapter(arrayAdapter);
        //end of Spinner

        //date, time
        date_time_in=findViewById(R.id.date_time_input);

        date_time_in.setInputType(InputType.TYPE_NULL);

        date_time_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimeDialog(date_time_in);
            }
        });

        reminderName = findViewById(R.id.reminderName);
        String rName = reminderName.getText().toString();
        addReminder = findViewById(R.id.addReminder);
        addReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(rName.equals(""))){
                    Toast.makeText(ReminderFormActivity.this, "reminder was added", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ReminderFormActivity.this, RemindersActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(ReminderFormActivity.this, "You need to full ALL the form to add!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        //end date and time

        //add reminder to db
        reminderName = findViewById(R.id.reminderName);
        addReminder= findViewById(R.id.addReminder);
//        tasksSpinner = findViewById(R.id.reminderIntensityLevel);

        /*notification ~ s3 | 2 */
        notificationManager = NotificationManagerCompat.from(this);
        /*notification ~ s3 | end 2*/

        /*notification ~ s3 | to creat notf when the add btn click*/
        addReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tname = reminderName.getText().toString(),
                        ti = tasksSpinner.getSelectedItem().toString(),
                        dateTime = date_time_in.getText().toString();
                if(!(tname.equals("")||dateTime.equals(""))){
                    Reminder reminder = new Reminder(tname,ti, dateTime);

//                    Toast.makeText(ReminderFormActivity.this, "reminder has been added with name= "+tname+ "and i= "+ti+" date "+dateTime, Toast.LENGTH_SHORT).show();
                    db d = new db(ReminderFormActivity.this);
                    d.addReminder(reminder);
                    Intent intent = new Intent(ReminderFormActivity.this, RemindersActivity.class);
                    intent.putExtra("reminder",reminder );
                    startActivity(intent);

                    /*notification ~ s3 | 3 */
                    Notification notification = new NotificationCompat.Builder(ReminderFormActivity.this, Notf.CHANNEL_1_ID)
                            .setSmallIcon(R.drawable.ic_notification)
                            .setContentTitle(tname)
                            .setContentText("Intensity level: "+ti)
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                            .build();

                    /*set time */
                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                    long timeAtButtonClick = System.currentTimeMillis();
                    long time = timeAtButtonClick+ 1000*10;//dateTime.substring()

//                    alarmManager.set(AlarmManager.RTC_WAKEUP,
//                            time,
//                            );
                    /*display notf*/
                    notificationManager.notify(Integer.parseInt(reminder.getId()),notification);

                    /*notification ~ s3 | end 3*/

                }else {
                    Toast.makeText(ReminderFormActivity.this, "you need to full ALL the form to add the reminder", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //end add reminder to db


    }
    /*notification ~ s2*/
    /*notification ~ s2 end*/


    //for date and time
    private void showDateTimeDialog(final EditText date_time_in) {
        final Calendar calendar=Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        calendar.set(Calendar.MINUTE,minute);

                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yy-MM-dd HH:mm");

                        date_time_in.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                };

                new TimePickerDialog(ReminderFormActivity.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
            }
        };

        new DatePickerDialog(ReminderFormActivity.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

    }
}