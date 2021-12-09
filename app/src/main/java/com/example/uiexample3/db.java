package com.example.uiexample3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Random;

public class db extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME= "FittMinder.db";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_NAME= "Task";
    private static final String ID= "taskID";
    private static final String NAME = "tName";
    private static final String INTENSITY_LEVEL= "intensityLevel";
    private static final String CHEKED = "cheked";

    private static final String RTABLE_NAME= "Reminder";
    private static final String RID= "reminderID";
    private static final String RNAME = "rName";
    private static final String RINTENSITY_LEVEL= "intensityLevel";
    private static final String RDATE = "date";
    private static final String RCHEKED = "cheked";

    public db(@Nullable Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context= context;
//        this.onCreate((SQLiteDatabase)this);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + ID + " TEXT NOT NULL, " +
                NAME + " TEXT NOT NULL, " +
                INTENSITY_LEVEL + " TEXT NOT NULL, " +
                CHEKED + " TEXT NOT NULL, " +
                "PRIMARY KEY ("+ID+")"+
                ");";

        sqLiteDatabase.execSQL(query);

        String query1 = "CREATE TABLE " + RTABLE_NAME+
                " (" + RID + " TEXT NOT NULL, " +
                RNAME + " TEXT NOT NULL, " +
                RINTENSITY_LEVEL + " TEXT NOT NULL, " +
                RCHEKED + " TEXT NOT NULL, " +
                RDATE + " TEXT NOT NULL, " +
                "PRIMARY KEY ("+RID+")"+
                ");";

        sqLiteDatabase.execSQL(query1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+RTABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addTask(Task task){

        SQLiteDatabase d = this.getWritableDatabase();
        ContentValues cv =new ContentValues();

        cv.put("taskID", task.getId());
        cv.put(NAME, task.getName());
        cv.put(INTENSITY_LEVEL, task.getIntensityLevel());
        cv.put(CHEKED, task.getCheacked());

        long r= d.insert(TABLE_NAME,null,cv);
//        System.out.println(r);
//        SQLiteDatabase database = this.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//
//        cv.put(ID, task.getId());
//        cv.put(NAME, task.getName());
//        cv.put(INTENSITY_LEVEL, task.getIntensityLevel());
//        if(task.getCheacked())
//            cv.put(CHEKED, 1);
//        else
//            cv.put(CHEKED, 0);
//
//        long r = database.insert(TABLE_NAME, null, cv);
        if(r == -1){
            Toast.makeText(context, "failed to add", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(context, "Added successfully!", Toast.LENGTH_SHORT).show();
    }

    public void addReminder(Reminder reminder){

        SQLiteDatabase d = this.getWritableDatabase();
        ContentValues cv =new ContentValues();

//        Toast.makeText(context, "reminder has been added with name= "+reminder.getName()+ "and i= "+reminder.getId()+" date "+reminder.getDate(), Toast.LENGTH_SHORT).show();
        cv.put(RID, reminder.getId());
        cv.put(RNAME, reminder.getName());
        cv.put(RINTENSITY_LEVEL, reminder.getIntensityLevel());
        cv.put(RCHEKED, reminder.getCheacked());
        cv.put(RDATE, reminder.getDate());

        long r= d.insert(RTABLE_NAME,null,cv);
//        System.out.println(r);
        if(r == -1){
            Toast.makeText(context, "failed to add", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(context, "Added successfully!", Toast.LENGTH_SHORT).show();
    }

    public Cursor readAllData(){
        String query = "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase d = this.getReadableDatabase();

        Cursor cursor = null;
        if(d !=null){
            cursor= d.rawQuery(query,null);
        }
        return cursor;
    }

    public Cursor readAllReminder(){
        String query = "SELECT * FROM "+RTABLE_NAME;
        SQLiteDatabase d = this.getReadableDatabase();

        Cursor cursor = null;
        if(d !=null){
            cursor= d.rawQuery(query,null);
        }
        return cursor;
    }

    public String changeCheack(long id){//for task
        String cheaked = "1",go="";
        String query = "SELECT * FROM "+TABLE_NAME+" WHERE "+ID+" = "+ id;
        SQLiteDatabase d = this.getReadableDatabase();

        Cursor cursor = null;
        if(d !=null){
            cursor= d.rawQuery(query,null);

        }

        if(cursor.getCount() == 0){
            Toast.makeText(context, "wrong id", Toast.LENGTH_SHORT).show();


        }else {
            if (cursor.moveToNext()){


                cheaked = cursor.getString(3);//0
//                o= ;

                SQLiteDatabase d1 = this.getReadableDatabase();
                ContentValues cv = new ContentValues();

                cv.put(ID,cursor.getString(0)); //These Fields should be your String values of actual column names
                cv.put(NAME,cursor.getString(1));
                cv.put(INTENSITY_LEVEL,cursor.getString(2));

                if(cursor.getString(3).equals("0")){
                    cv.put(CHEKED,"1");
                    go ="1";
                    Toast.makeText(context, "Will done!", Toast.LENGTH_SHORT).show();
                }else{
                    cv.put(CHEKED,"0");
                    go ="0";
                }

                d1.update(TABLE_NAME, cv, "taskID = ?", new String[]{""+id});;

            }
        }
        return go;
    }

    public String changeCheackReminder(long id){
        String cheaked = "1",go="";
        String query = "SELECT * FROM "+RTABLE_NAME+" WHERE "+RID+" = "+ id;
        SQLiteDatabase d = this.getReadableDatabase();

        Cursor cursor = null;
        if(d !=null){
            cursor= d.rawQuery(query,null);

        }

        if(cursor.getCount() == 0){
            Toast.makeText(context, "wrong id", Toast.LENGTH_SHORT).show();


        }else {
            if (cursor.moveToNext()){


                cheaked = cursor.getString(3);//0
//                o= ;

                SQLiteDatabase d1 = this.getReadableDatabase();
                ContentValues cv = new ContentValues();

                cv.put(RID,cursor.getString(0)); //These Fields should be your String values of actual column names
                cv.put(RNAME,cursor.getString(1));
                cv.put(RINTENSITY_LEVEL,cursor.getString(2));

                if(cursor.getString(3).equals("0")){
                    cv.put(RCHEKED,"1");
                    go ="1";
                    Toast.makeText(context, "Will Done", Toast.LENGTH_SHORT).show();
                }else{
                    cv.put(RCHEKED,"0");
                    go ="0";
                }

                cv.put(RDATE,cursor.getString(4));

                d1.update(RTABLE_NAME, cv, "reminderID = ?", new String[]{""+id});;

            }
        }
        return go;
    }

    /*Delete*/
    public boolean deleteTask(long id){

        SQLiteDatabase d = this.getReadableDatabase();

        String whereClause = "taskID=?";
        String[] whereId = new String[] { ""+id };
        return d.delete(TABLE_NAME, whereClause, whereId)>0;
    }

    /*Delete*/
    public boolean deleteReminder(long id){

        SQLiteDatabase d = this.getReadableDatabase();

        String whereClause = RID+"=?";
        String[] whereId = new String[] { ""+id };
        return d.delete(RTABLE_NAME, whereClause, whereId)>0;
    }


}
