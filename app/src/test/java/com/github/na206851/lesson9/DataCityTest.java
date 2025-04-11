package com.github.na206851.lesson9;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;

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
}