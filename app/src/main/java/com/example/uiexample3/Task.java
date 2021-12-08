package com.example.uiexample3;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.Random;

public class Task implements Parcelable {
    protected Task(Parcel in) {
        taskID = in.readString();
        name = in.readString();
        intensityLevel = in.readString();
        cheacked = in.readString();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(taskID);
        parcel.writeString(name);
        parcel.writeString(intensityLevel);
        parcel.writeString(cheacked);
    }

    private String taskID;
    private String name;
    private String intensityLevel;
    private String cheacked;

    public Task(String n, String i){
        //TODO validation for name, intensity
        name= n;
        intensityLevel=i;
        cheacked="0";
        //gnrat id
        Random random= new Random();
        int ids[]= new int[6];
//        ids[0] = random.nextInt(90-65)+65;//0-9
//        ids[1] = random.nextInt(90-65)+65;
//        ids[2] = random.nextInt(122-97)+97;
//        ids[3] = random.nextInt(122-97)+97;
        ids[4] = random.nextInt(10);
        ids[5] = random.nextInt(10);
        taskID= ""+ids[4]+ids[5];
    }

    public void displayTaskInfo(){
        //TODO displayTaskInfo
    }

    public void cheackTask(){
        if(cheacked.equals("0"))
            cheacked= "1";
        else cheacked = "0";
    }

    public boolean deleteTask(){
        //TODO deleteTask
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
        return taskID;
    }

    public String toString(){
        return name;
    }

}
