package me.seohuipark.noblewaybackend.common.code;

import lombok.AllArgsConstructor;
import java.util.function.DoubleBinaryOperator;

@AllArgsConstructor
public enum OperationCode {

    PLUS("001", "+", (number1, number2) -> number1 + number2),
    MINUS("002", "-", (number1, number2) -> number1 - number2),
    MULTIPLY("003", "*", (number1, number2) -> number1 * number2),
    DIVIDE("004", "/", (number1, number2) -> number1 / number2);

    private final String code;
    private final String symbol;
    private final DoubleBinaryOperator expression;

    public double calculate(double number1, double number2) {
        return expression.applyAsDouble(number1, number2);
    }
}