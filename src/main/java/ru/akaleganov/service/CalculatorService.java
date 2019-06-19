package ru.akaleganov.service;

import ru.akaleganov.err.OperationError;
import ru.akaleganov.model.Task;

import java.util.HashMap;
import java.util.function.Consumer;

/**
 * Класс для вычисления функций
 *
 * @author Alexander Kaleganov
 * @version 1/0
 * <br/>
 * <b>класс содержит поля:</b>
 * @see CalculatorService#functionsOfTheCalculator
 * @see CalculatorService#CALCULATOR_SERVICE
 * @since 18.06.2019г
 */
public class CalculatorService {
    /**
     * хеш карта, которая содержит в себе различные фукии,
     * ключём от функции является строковое значение указвающее на необходимое дествие "+", "-", "/", "*"
     */
    private final HashMap<String, Consumer<Task>> functionsOfTheCalculator = new HashMap<>();
    /**
     * синглтон текущего класса
     */
    private final static CalculatorService CALCULATOR_SERVICE = new CalculatorService().init();

    /**
     * инициализация хеш карты
     *
     * @return {@link CalculatorService}  возвращает проинициализированный объект текущего класса
     */
    private CalculatorService init() {
        this.functionsOfTheCalculator.put("+", (task) -> task.setResult((float) task.getX() + (float) task.getY()));
        this.functionsOfTheCalculator.put("-", (task) -> task.setResult((float) task.getX() - (float) task.getY()));
        this.functionsOfTheCalculator.put("*", (task) -> task.setResult((float) task.getX() * (float) task.getY()));
        this.functionsOfTheCalculator.put("/", (task) -> task.setResult((float) task.getX() / (float) task.getY()));
        return this;
    }

    /*
     * @param task {@link Task}  принимает задачу, которую необходимо решить
     *             в задаче указывается действие "+", "-", "/", "*" по данному полю
     *             происходит вызов необходимой функции и далее задача передаётся в функцию в которой она обрабатывается,
     *             и в поле задачи result  заносится результат
     * @throws OperationError метод выбросит исключение если действие не обнаружео
     */
    public void count(Task task) throws OperationError {
        if (this.functionsOfTheCalculator.containsKey(task.getMathematicalSymbol())) {
            this.functionsOfTheCalculator.get(task.getMathematicalSymbol()).accept(task);
        } else {
            throw new OperationError();
        }
    }

    /**
     * @return {@link CalculatorService}  получение инициализированного объекта текущего класса
     */
    public static CalculatorService getCalculatorService() {
        return CALCULATOR_SERVICE;
    }
}
