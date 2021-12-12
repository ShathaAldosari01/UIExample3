package com.example.uiexample3;

import static org.junit.Assert.*;

import org.junit.Test;

public class ReminderTest {

    @Test
    public void validName() {
        System.out.println("validName");
        Reminder reminder = new Reminder("Runing", "High", "22-10-10 10:10");//"yy-MM-dd HH:mm"
        boolean valid = reminder.validName("Runnig ");
        assertTrue(valid);
    }

    @Test
    public void validIntensity() {
        System.out.println("validIntensity");
        Reminder reminder = new Reminder("Runing", "High", "22-10-10 10:10");//"yy-MM-dd HH:mm"
        boolean valid = reminder.validIntensity("High");
        assertTrue(valid);
    }

    @Test
    public void reminderInfo() {
        System.out.println("reminderInfo");
        Reminder reminder = new Reminder("Runing", "High", "22-10-10 10:10");
        String[] realInfo = {reminder.getId(), "Runing", "High", reminder.getDate(), "0"},
                info = reminder.reminderInfo();
        assertArrayEquals(realInfo, info);
    }

    @Test
    public void cheackReminder() {
        System.out.println("cheackReminder");
        Reminder reminder = new Reminder("Runing", "High", "22-10-10 10:10");
        String ch = reminder.cheackReminder();
        assertEquals("1", ch);

    }

    @Test
    public void getName() {
        System.out.println("getName");
        Reminder reminder = new Reminder("Runing", "High", "22-10-10 10:10");
        String name= reminder.getName();
        assertEquals("Runing", name);
    }

    @Test
    public void getIntensityLevel() {
        System.out.println("getIntensityLevel");
        Reminder reminder = new Reminder("Runing", "High", "22-10-10 10:10");
        String intensityLevel= reminder.getIntensityLevel();
        assertEquals("High", intensityLevel);
    }

    @Test
    public void getCheacked() {
        System.out.println("getCheacked");
        Reminder reminder = new Reminder("Runing", "High", "22-10-10 10:10");
        String cheacked= reminder.getCheacked();
        assertEquals("0", cheacked);
    }

    @Test
    public void validDate(){
        System.out.println("validDate");
        Reminder reminder= new Reminder("Runing", "High", "21-10-10 10:10");
        boolean valid = reminder.validDate("22-10-10 10:10");
        assertTrue(valid);


    }

    @Test
    public void getDate(){
        System.out.println("getDate");
        Reminder reminder = new Reminder("Runing", "High", "22-10-10 10:10");
        String date = reminder.getDate();
        assertEquals("22-10-10 10:10", date);
    }
}