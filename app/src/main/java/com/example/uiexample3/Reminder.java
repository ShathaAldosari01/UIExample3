package com.example.uiexample3;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class Reminder implements Parcelable {
    protected Reminder(Parcel in) {
        reminderID = in.readString();
        name = in.readString();
        intensityLevel = in.readString();
        cheacked = in.readString();
        date = in.readString();
    }

    public static final Creator<Reminder> CREATOR = new Creator<Reminder>() {
        @Override
        public Reminder createFromParcel(Parcel in) {
            return new Reminder(in);
        }

        @Override
        public Reminder[] newArray(int size) {
            return new Reminder[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(reminderID);
        parcel.writeString(name);
        parcel.writeString(intensityLevel);
        parcel.writeString(cheacked);
        parcel.writeString(date);
    }

    private String reminderID;
    private String name;
    private String intensityLevel;
    private String date; //yy-mm-dd hh:mm
    private String cheacked;

    public Reminder(String n, String i, String date){
        if(validName(n))
            name= n;
        else
            name ="Walking";
        if(validIntensity(i))
            intensityLevel=i;
        else
            intensityLevel="Low";
        cheacked="0";
        if(validDate(date))
            this.date = date;
        else {
            long yourmilliseconds = System.currentTimeMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm");
            Date resultdate = new Date(yourmilliseconds);
            this.date = sdf.format(resultdate);
        }
        //gnrat id
        Random random= new Random();
        int ids[]= new int[6];
//        ids[0] = random.nextInt(90-65)+65;//0-9
//        ids[1] = random.nextInt(90-65)+65;
//        ids[2] = random.nextInt(122-97)+97;
//        ids[3] = random.nextInt(122-97)+97;
        ids[0] = random.nextInt(10);
        ids[1] = random.nextInt(10);
        ids[2] = random.nextInt(10);
        ids[3] = random.nextInt(10);
        ids[4] = random.nextInt(10);
        ids[5] = random.nextInt(10);
        reminderID= ""+ids[5]+ids[4]+ids[3]+ids[2]+ids[1]+ids[0];
    }

    public boolean validName(String n){
        return n!="";
    }

    public boolean validDate(String d) {
        String myDate = d;
//creates a formatter that parses the date in the given format
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm");
        try {
            Date date = sdf.parse(myDate);
            long timeInMillis = date.getTime();

            if (timeInMillis>System.currentTimeMillis())
                return true;
        }catch (ParseException e){
            return false;
        }
        return false;
    }
    public boolean validIntensity(String i){
        if(i.equalsIgnoreCase("Low")||i.equalsIgnoreCase("Moderate")||i.equalsIgnoreCase("High"))
            return true;
        return false;
    }

    public String[] reminderInfo(){
        String[] info = {reminderID, name, intensityLevel,date, cheacked };
        return info;
    }

    public String cheackReminder(){
        if(cheacked.equals("0")){
            cheacked= "1";
            return cheacked;
        }
        cheacked = "0";
        return cheacked;
    }



    //Getter, Setters
    public String getName(){
        return name;
    }

    public String getIntensityLevel(){
        return intensityLevel;
    }

    public String getCheacked(){
        return cheacked;
    }

    public String getId(){
        return reminderID;
    }

    public String toString(){
        return name;
    }

    public String getDate(){
        return date;
    }

}

