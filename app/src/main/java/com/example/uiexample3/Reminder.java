package com.example.uiexample3;

import android.os.Parcel;
import android.os.Parcelable;

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
        //TODO validation for name, intensity
        name= n;
        intensityLevel=i;
        cheacked="0";
        this.date = date;
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

    public void displayReminderInfo(){
        //TODO displayReminderInfo
    }

    public void cheackReminder(){
        if(cheacked.equals("0"))
            cheacked= "1";
        else cheacked = "0";
    }

    public boolean deleteReminder(){
        //TODO deleteReminder
        return true;
    }

    public boolean approveDeletion(boolean b){
        //TODO approveDeletion
        return true;
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

