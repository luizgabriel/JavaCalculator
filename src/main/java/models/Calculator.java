package models;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.*;

public class Calculator {

    private String expression = "";
    private double decimal = 1;
    private double currentNumber = 0;
    private final ScriptEngine engine;
    private final Set<Character> operators = new HashSet<>(Arrays.asList(
            '/', '*', '-', '+', '=', '.'
    ));

    public Calculator() {
        ScriptEngineManager mgr = new ScriptEngineManager();
        engine = mgr.getEngineByName("JavaScript");
    }

    public void appendToken(Character token) {
        if (operators.contains(token))
            appendSymbol(token);
        else
            appendNumber(Integer.parseInt(token.toString()));
    }

    private void appendNumber(int number) {
        if (decimal < 1) {
            currentNumber += number * decimal;
            decimal *= 0.1;
        } else {
            currentNumber *= 10;
            currentNumber += number;
        }
    }

    private void appendSymbol(Character symbol) {
        switch (symbol) {
            case '.':
                if (decimal == 1)
                    decimal *= 0.1;
                break;
            default:
                expression += currentNumber + " " + symbol + " ";
                currentNumber = 0;
                decimal = 1;
                break;
        }
    }

    public String getExpression() {
        return expression;
    }

    public double getCurrentNumber() {
        return currentNumber;
    }

    public double getResult() {
        try {
            expression += " " + currentNumber;
            return (double) engine.eval(expression);
        } catch (ScriptException | NullPointerException e) {
            return currentNumber;
        }
    }

    public void clear() {
        decimal = 1;
        currentNumber = 0;
        expression = "";
    }

    public void appendExpression(String expression) {
        for (char c : expression.toCharArray()) {
            if (c != ' ')
                appendToken(c);
        }
    }
}
