package ru.akaleganov.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class TaskTest {

    @Test
    public void tesCreateTask() {
        Task task = new Task();
        task.setX(1);
        task.setY(2);
        task.setResult(3.0f);
        task.setMathematicalSymbol("+");
        assertEquals("+", task.getMathematicalSymbol());
        assertEquals(1, task.getX());
        assertEquals(2, task.getY());
        assertEquals(3.0, task.getResult(), 0.0);

    }
}