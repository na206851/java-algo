package com.github.na206851.lesson5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {
    Calculator test = new Calculator();

    @Test
    void parse() {
        List<String> list = List.of("2", "+", "2");
        Assertions.assertLinesMatch(list, test.parseString("2+2"));
    }

    @Test
    void parseBraces() {
        List<String> list = List.of("(", "2", "+", "2", ")");
        Assertions.assertLinesMatch(list, test.parseString("(2+2)"));
    }

    @Test
    void parseThreeOperation() {
        List<String> list = List.of("(", "2", "+", "2", ")", "+", "3");
        Assertions.assertLinesMatch(list, test.parseString("(2+2)+3"));
    }

    @Test
    void parseBigNums() {
        List<String> list = List.of("10", "+", "10");
        Assertions.assertLinesMatch(list, test.parseString("10+10"));
    }

    @Test
    void parseBigNumsAndBraces() {
        List<String> list = List.of("(", "10", "+", "10", ")", "*", "11");
        Assertions.assertLinesMatch(list, test.parseString("(10+10)*11"));
    }

    @Test
    void parseNegativeDigitIsFirst() {
        List<String> list = List.of("-10", "+", "10", "*", "(", "-2", ")");
        Assertions.assertLinesMatch(list, test.parseString("-10+10*(-2)"));
    }

    /**
     * Test with strToPolyshNotation
     */
    @Test
    void polishNotationAdditionCase() {
        List<String> list = List.of("10", "10", "+");
        Assertions.assertLinesMatch(list, test.infixToPolishNotation(List.of("10", "+", "10")));
    }

    @Test
    void polishNotationThreeDigit() {
        List<String> list = List.of("2", "2", "2", "*", "+");
        Assertions.assertLinesMatch(list, test.infixToPolishNotation(List.of("2", "+", "2", "*", "2")));
    }

    @Test
    void polishNotationDifferenceThreeOperation() {
        List<String> list = List.of("1", "2", "3", "+", "+");
        Assertions.assertLinesMatch(list, test.infixToPolishNotation(List.of("1", "+", "2", "+", "3")));
    }

    @Test
    void polishNotationAdditionAndMultiplication() {
        List<String> list = List.of("1", "2", "3", "*", "+");
        Assertions.assertLinesMatch(list, test.infixToPolishNotation(List.of("1", "+", "2", "*", "3")));
    }

    @Test
    void polishNotationWithBraces() {
        List<String> list = List.of("2", "2", "+", "2", "*");
        Assertions.assertLinesMatch(list, test.infixToPolishNotation(List.of("(", "2", "+", "2", ")", "*", "2")));
    }

    @Test
    void polishNotationBracesInTheEnd() {
        List<String> list = List.of("5", "2", "10", "*", "+");
        Assertions.assertLinesMatch(list, test.infixToPolishNotation(List.of("5", "+", "(", "2", "*", "10", ")")));
    }

    @Test
    void polishNotationBigDigitsWithBraces() {
        List<String> list = List.of("10", "10", "10", "+", "*");
        Assertions.assertLinesMatch(list, test.infixToPolishNotation(List.of("10", "*", "(", "10", "+", "10", ")")));
    }

    @Test
    void polishNotationUnaryOperator() {
        List<String> list = List.of("1", "-");
        Assertions.assertLinesMatch(list, test.infixToPolishNotation(List.of("-", "1")));
    }

    @Test
    void polishNotationUnaryOperatorWithTwoOperation() {
        //2+2+(-2)
        List<String> list = List.of("1", "2", "-3", "+", "+");
        Assertions.assertLinesMatch(list, test.infixToPolishNotation(List.of("1", "+", "2", "+", "(", "-3", ")")));
    }

    @Test
    void EmmaTestPolishNotationWithUnaryOperator() {
        List<String> expected1 = Arrays.asList("-122", "2", "+", "3", "*");
        Assertions.assertLinesMatch(expected1, new Calculator().infixToPolishNotation(Arrays.asList("(", "-122", "+", "2", ")", "*", "3")));
    }

    @Test
    void EmmaTestPolishNotatioin() {
        List<String> expected2 = Arrays.asList("-122", "2", "+", "-3", "*");
        Assertions.assertLinesMatch(expected2, new Calculator().infixToPolishNotation(Arrays.asList("(", "(", "-122", "+", "2", ")", "*", "-3", ")")));
    }

    @Test
    void EmmaTestMultiplicationNegativeNumber() {
        List<String> expected3 = Arrays.asList("5", "-1", "*", "-2", "-");
        Assertions.assertLinesMatch(expected3, new Calculator().infixToPolishNotation(Arrays.asList("(", "5", "*", "-1", ")", "-", "-2")));
    }

    @Test
    void EmmaTestNegativeDigitSubtractionInBracesAndMultiplication() {
        List<String> expected4 = Arrays.asList("5", "-1", "-2", "-", "*");
        Assertions.assertLinesMatch(expected4, new Calculator().infixToPolishNotation(Arrays.asList("5", "*", "(", "-1", "-", "-2", ")")));
    }

    @Test
    void EmmaTestSubtractionInBracesAndMultiplication() {
        List<String> expected5 = Arrays.asList("5", "1", "2", "-", "*");
        Assertions.assertLinesMatch(expected5, new Calculator().infixToPolishNotation(List.of("5", "*", "(", "1", "-", "2", ")")));
    }

    @Test
    void EmmaTest6() {
        List<String> expected6 = Arrays.asList("5", "-1", "-2", "-", "*");
        Assertions.assertLinesMatch(expected6, new Calculator().infixToPolishNotation(Arrays.asList("5", "*", "(", "-1", "-", "(", "-2", ")", ")")));
    }

    /**
     * test evanRPN method
     */
    @Test
    void testEvanRPNMethodAddition() {
        int expected = 4;
        assertEquals(expected, test.evalRPN(List.of("2", "2", "+")));
    }

    @Test
    void testEvanRPNAdditionThreeNums() {
        int expected = 33;
        assertEquals(expected, test.evalRPN(List.of("10", "11", "12", "+", "+")));
    }

    @Test
    void testEvanRPNLeecodeCase() {
        int expected = 22;
        assertEquals(expected, test.evalRPN(List.of("10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+")));
    }

    @Test
    void testEvanRPNDivisionCase() {
        int expected = 6;
        assertEquals(expected, test.evalRPN(List.of("4", "13", "5", "/", "+")));
    }

    @Test
    void testEvamRPNNegativeResult() {
        int expected = -1;
        assertEquals(expected, test.evalRPN(List.of("3", "-4", "+")));
    }

    @Test
    void testAdditionInBracesAndMultiplicationCase() {
        int expected = 9;
        assertEquals(expected, test.evalRPN(List.of("2", "1", "+", "3", "*")));
    }


}