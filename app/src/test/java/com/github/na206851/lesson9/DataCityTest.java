package com.github.na206851.lesson9;

import com.sun.source.tree.AssertTree;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataCityTest {
    //    dataCity;
    public DataCity createData() {
        DataCity dataCity = new DataCity();
        dataCity.addCity("анадырь");
        dataCity.addCity("архангельск");
        dataCity.addCity("брянск");
        dataCity.addCity("будапешт");
        dataCity.addCity("будва");

        dataCity.addCity("владимир");
        dataCity.addCity("вологда");
        dataCity.addCity("грозный");
        dataCity.addCity("гватемала");
        dataCity.addCity("донецк");
        dataCity.addCity("екатеринбург");
        dataCity.addCity("елань");
        dataCity.addCity("б");
        dataCity.addCity("будва");
        return dataCity;
    }

    @Test
    public void positiveCase0() {
        DataCity data = createData();
        List<String> exp = List.of("б", "брянск", "будапешт", "будва");
        List<String> act = data.searchCity("б");
        System.out.println(act);
        assertLinesMatch(exp, act);
    }

    @Test
    public void positiveCase1() {
        DataCity data = createData();
        List<String> exp = List.of("владимир", "вологда");
        List<String> act = data.searchCity("в");
        Assertions.assertLinesMatch(exp, act);
    }

    @Test
    public void positiveCase2() {
        DataCity data = createData();
        List<String> exp = List.of("будапешт", "будва");
        List<String> act = data.searchCity("буд");
        Assertions.assertLinesMatch(exp, act);
    }

    @Test
    public void missCity0() {
        DataCity data = createData();
        List<String> exp = new ArrayList<>();
        List<String> act = data.searchCity("улан");
        assertLinesMatch(exp, act);
    }

    @Test
    public void missCity1() {
        DataCity data = createData();
        List<String> exp = new ArrayList<>();
        List<String> act = data.searchCity("пешт");
        assertLinesMatch(exp, act);
    }

    @Test
    public void equals() {
        String first = "moscow";
        String second = new String("mosco");
        int stringFirstSumCharSymbol = 0;
        int stringSecondSumCharSymbol = 0;
        for (char c : first.toCharArray()) {
            stringFirstSumCharSymbol += c;
        }
        for (char c : second.toCharArray()) {
            stringSecondSumCharSymbol += c;
        }

        assertNotEquals(stringFirstSumCharSymbol, stringSecondSumCharSymbol);
    }

    @Test
    public void equalsUpperCase() {
        String first = "moscow";
        String second = new String("MOSCOW");

        assertNotEquals(first.toCharArray()[0], second.toCharArray()[0]);
    }

    @Test
    public void equals2() {
        String first = "mos";
        String second = "moS";
        int i = 0;
        int result = 0;
        char[] secondCharArray = second.toCharArray();
        for (char c : first.toCharArray()) {
            if (c != secondCharArray[i]) {
                result = (int) c - secondCharArray[i];
            }
            i++;
        }
        assertNotEquals(0, result);
    }

    @Test
    public void charArrayNotEquals() {
        String first = "moscow";
        String second = "moscod";
        int[] arrFirst = new int[first.length()];
        int[] arrSecond = new int[first.length()];
        System.out.println(Arrays.toString(first.toCharArray()));
        int i = 0;
        for (char c : first.toCharArray()) {
            arrFirst[i++] = (int) c;
        }
        i = 0;
        for (char c : second.toCharArray()) {
            arrSecond[i++] = (int) c;
        }
        assertNotEquals(arrFirst, arrSecond);
    }

    @Test
    public void testEqualsString() {
        String first = "будва";
        String second = "будапешт";
        int countFirst = 0;
        int countSecond = 0;
        String result = "";
        while (countFirst != first.length() && countSecond != second.length()) {
            if (first.charAt(countFirst) != second.charAt(countSecond)) {
                result = "строки не равны";
            } else {
                result = "строки равны";
            }
            countSecond++;
            countFirst++;
        }
        assertEquals("строки не равны", result);
    }

    @Test
    public void testEqualsString2() {
        String first = "будва";
        String second = "будапешт";
        assertNotEquals(first, second);
    }

    @Test
    public void testEqualsString3() {
        String prefix = "byd";

        List<String> list = List.of("b", "byd", "bydwa", "moscow", " buffalo");
        List<String> expected = List.of("byd", "bydwa");
        List<String> act = new ArrayList<>();

        for (String str : list) {
            if (prefix.compareTo(str) <= 0 && (prefix + Character.MAX_VALUE).compareTo(str) > 0) {
                act.add(str);
            }
        }

        System.out.println(prefix.compareTo("b") + " b");
        System.out.println(prefix.compareTo("byd") + " byd");
        System.out.println(prefix.compareTo("bydwa") + " bydwa");
        System.out.println(prefix.compareTo("moscow") + " moscow");
        System.out.println(prefix.compareTo("buffalo") + " buffalo");
        System.out.println(prefix.compareTo("buffalobydwaforever") + " buffalobydwaforever");
        System.out.println("\n" + "(prefix + Character.MAX_VALUE).compareTo(str)");
        System.out.println((prefix + Character.MAX_VALUE).compareTo("b") + " b");
        System.out.println((prefix + Character.MAX_VALUE).compareTo("byd") + " byd");
        System.out.println((prefix + Character.MAX_VALUE).compareTo("bydwa") + " bydwa");
        System.out.println((prefix + Character.MAX_VALUE).compareTo("moscow") + " moscow");
        System.out.println((prefix + Character.MAX_VALUE).compareTo("buffalo") + " buffalo");
        System.out.println((prefix + Character.MAX_VALUE).compareTo("buffalobydwaforevero") + " buffalobydwaforever");
        System.out.println("\n" + act);
        assertLinesMatch(expected, act);
    }

}