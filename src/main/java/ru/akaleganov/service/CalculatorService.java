package ru.akaleganov.service;

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
 * @since 18.06.2019г
 */
public class CalculatorService {
    /**
     * хеш карта, которая содержит в себе различные фукии,
     * ключём от функции является строковое значение указвающее на необходимое дествие "+", "-", "/", "*"
     */
    private final HashMap<String, Consumer<Task>> functionsOfTheCalculator = new HashMap<>();

    /**
     * инициализация хеш карты
     * @return {@link CalculatorService}  возвращает проинициализированный объект текущего класса
     */
    public CalculatorService init() {
        this.functionsOfTheCalculator.put("+", (task) -> task.setResult(task.getX() + task.getY()));
        this.functionsOfTheCalculator.put("-", (task) -> task.setResult(task.getX() - task.getY()));
        this.functionsOfTheCalculator.put("*", (task) -> task.setResult(task.getX() * task.getY()));
        this.functionsOfTheCalculator.put("/", (task) -> task.setResult(task.getX() / task.getY()));
        return this;
    }

    /**
     * @param task {@link Task}  принимает задачу, которую необходимо решить
     *             в задаче указывается действие "+", "-", "/", "*" по данному полю
     *             происходит вызов необходимой функции и далее задача передаётся в функцию в которой она обрабатывается,
     *             и в поле задачи result  заносится результат
     */
    public void count(Task task) {
        this.functionsOfTheCalculator.get(task.getMathematicalSymbol()).accept(task);
    }
}
