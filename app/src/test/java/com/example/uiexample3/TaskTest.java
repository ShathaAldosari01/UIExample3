package com.example.uiexample3;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TaskTest {

    @Test
    public void validName() {
        System.out.println("validName");
        Task task = new Task("Runing", "Low");
        boolean valid = task.validName("Runnig ");
        assertTrue(valid);
    }

    @Test
    public void validIntensity() {
        System.out.println("validIntensity");
        Task task = new Task("Runing", "Low");
        boolean valid = task.validIntensity("Low");
        assertTrue(valid);
    }

    @Test
    public void taskInfo() {
        System.out.println("taskInfo");
        Task task = new Task("Runing", "Low");
        String[] realInfo = {task.getId(), "Runing", "Low", "0"};
        String[] info = task.TaskInfo();
        assertArrayEquals(realInfo,info );

    }

    @Test
    public void cheackTask() {
        System.out.println("cheackTask");
        Task task = new Task("Runing", "Low");
        String ch = task.cheackTask();
        assertEquals("1", ch);

    }

    @Test
    public void getName() {
        System.out.println("getName");
        Task task = new Task("Runing", "Low");
        String name = task.getName();
        assertEquals("Runing", name);
    }

    @Test
    public void getIntensityLevel() {
        System.out.println("getIntensityLevel");
        Task task = new Task("Runing", "Low");
        String in = task.getIntensityLevel();
        assertEquals("Low", in);
    }

    @Test
    public void getCheacked() {
        System.out.println("getCheacked");
        Task task = new Task("Runing", "Low");
        String ch = task.getCheacked();
        assertEquals("0", ch);
    }
}