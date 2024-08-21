package com.github.na206851.lesson5;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.na206851.lesson5.Calculator.strToPolyshNotation;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;

class CalculatorTest {
    Calculator test = new Calculator();

    @Test
    void parse() {

    }

    @Test
    void infixTolishNotation() {
        //тестируем все случаи отдельно
        //fn + delete
        //control + option + o - комбинация приводит в порядок импорты
        List<String> infixExpression = List.of("2", "+", "3");
        List<String> expected = List.of("2", "3", "+");
        assertLinesMatch(expected, strToPolyshNotation(infixExpression));

    }


    @Test
    void testCalculatorAdditionCase() {
        Calculator test = new Calculator();
        // int resutl = test.stackCalculator("2+2");
        //assertEquals(4, resutl);
    }

    @Test
    void testCalculatorMultiplicationWithBraces() {
        Calculator test = new Calculator();
        //   assertEquals(8, test.stackCalculator(" (2+2)*2 "));
    }

    @Test
    void testCalculatorDivision() {
        // assertEquals(9, test.stackCalculator("13 / 2 + 3"));
    }


}