package ru.akaleganov.model;

/**
 * Класс задача, содержит в себе две переменные, метематический символ, и результат
 *
 * @author Alexandr Kaleganov
 * @version 1
 * <br/>
 * <b>содержит поля:<b/>
 * @see Task#x
 * @see Task#y
 * @see Task#mathematicalSymbol
 * @see Task#result
 * @since 18.06.19г.
 */
public class Task {
    /**
     * первая переменная
     */
    private int x;
    /**
     * вторая переменная
     */
    private int y;
    /**
     * математический символ
     */
    private String mathematicalSymbol;
    /**
     * результат вычисления
     */
    private float result;

    /**
     * @return {@see Task#x}
     */
    public int getX() {
        return x;
    }

    /**
     * @param x измениь значение первой переменной
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return {@see Task#y}
     */
    public int getY() {
        return y;
    }

    /**
     * @param y изменить значение переменной
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return {@see Task#mathematicalSymbol}
     */
    public String getMathematicalSymbol() {
        return mathematicalSymbol;
    }

    /**
     * @param mathematicalSymbol задать математический символ
     */
    public void setMathematicalSymbol(String mathematicalSymbol) {
        this.mathematicalSymbol = mathematicalSymbol;
    }

    /**
     * @return {@see Task#result}
     */
    public float getResult() {
        return result;
    }

    /**
     * @param result задать результат вычисления
     */
    public void setResult(float result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s = %s", x, y, mathematicalSymbol, result);
    }
}
