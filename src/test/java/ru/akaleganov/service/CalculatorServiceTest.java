package ru.akaleganov.service;

import org.junit.Before;
import org.junit.Test;
import ru.akaleganov.err.OperationError;
import ru.akaleganov.model.Task;

import static org.junit.Assert.*;

public class CalculatorServiceTest {
    private CalculatorService calculatorService;
    private Task task;

    /**
     * инициализацировать класс калькулятор
     * задать значение полей объекта task
     * инициализация происходит перед каждым тестированием
     */
    @Before
    public void load() {
        calculatorService = new CalculatorService().init();
        task = new Task();
        task.setX(4);
        task.setY(2);
    }

    /**
     * тестирование сложения
     */
    @Test
    public void calculatorTestAddition() throws OperationError {
        task.setMathematicalSymbol("+");
        calculatorService.count(task);
        assertEquals(6.0, task.getResult(), 0.0);
    }

    /**
     * тестирование вычитания
     */
    @Test
    public void calculatorTestSubtraction() throws OperationError {
        task.setMathematicalSymbol("-");
        calculatorService.count(task);
        assertEquals(2.0, task.getResult(), 0.0);
    }

    /**
     * тестирование умножение
     */
    @Test
    public void calculatorTestMultiplication() throws OperationError {
        task.setMathematicalSymbol("*");
        calculatorService.count(task);
        assertEquals(8.0, task.getResult(), 0.0);
    }

    /**
     * тестирование деление
     */
    @Test
    public void calculatorTestDivision() throws OperationError {
        task.setMathematicalSymbol("/");
        calculatorService.count(task);
        assertEquals(2.0, task.getResult(), 0.0);
    }

    /**
     * тестирование исключения
     */
    @Test(expected = OperationError.class)
    public void calculatorTestOperationError() throws OperationError {
        task.setMathematicalSymbol("545");
        calculatorService.count(task);
    }

}