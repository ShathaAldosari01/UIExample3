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
        if(validName(n))
            name= n;
        else
            name= "Walking";
        if(validIntensity(i))
            intensityLevel=i;
        else
            intensityLevel= "Low";
        cheacked="0";
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
        taskID= ""+ids[5]+ids[4]+ids[3]+ids[2]+ids[1]+ids[0];
    }

    public boolean validName(String n){
        return n!="";
    }

    public boolean validIntensity(String i){
        if(i.equalsIgnoreCase("Low")||i.equalsIgnoreCase("Moderate")||i.equalsIgnoreCase("High"))
            return true;
        return false;
    }

    public String[] TaskInfo(){
         String[] info = {taskID, name, intensityLevel,cheacked };
         return info;
    }

    public String cheackTask(){
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
        return taskID;
    }

    public String toString(){
        return name;
    }

}
