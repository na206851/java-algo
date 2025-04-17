package com.github.na206851.lesson9;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class DataCity {
    private final TreeMap<String, String> dataCity = new TreeMap<>();   //создаем базу данных 

    public void addCity(String city) {
        String key = city.toLowerCase();    //задаем ключ по которому будет храниться город
        dataCity.put(key, city);            //добавляем пару ключ значение города
    }

    public List<String> searchCity(String searchElement) {
        String startAreaSearch = searchElement.toLowerCase();   //нижняя граница поиска
        String endAreaSearch = startAreaSearch + Character.MAX_VALUE;   //верхняя граница поиска
        return new ArrayList<>(dataCity.subMap(startAreaSearch, endAreaSearch).values());   //задаем границу поиска и возвращаем лист
    }

    public String toString() {
        return dataCity.toString();
    }
}