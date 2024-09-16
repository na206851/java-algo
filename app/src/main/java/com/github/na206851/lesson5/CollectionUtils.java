package com.github.na206851.lesson5;


import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class CollectionUtils {
    public static void main(String[] args) {
        CollectionUtils test = new CollectionUtils();
        Employee one = new Employee("amur");
        Employee two = new Employee("mira");
        Employee three = new Employee("cat");

// так должно быть Assertions.assertIterableEquals(expected1, new CollectionUtils().transformNewCollectionType(employees1, employeeStringRepresentation));

        List<String> listDigit = List.of("1", "2", "3", "4");
        // Assertions.assertIterableEquals(expected1, new CollectionUtils().transformNewCollectionType(employees1, employeeStringRepresentation));
        Function<String, Integer> listDigitTransform = Integer::parseInt;
        System.out.println(test.transformNewCollectionType(listDigit, listDigitTransform));
        System.out.println(test.transformNewCollectionType(listDigit, listDigitTransform).get(0));
        System.out.println(test.transformNewCollectionType(listDigit, listDigitTransform).get(0).getClass());


    }

    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    public <T, R> List<R> transformNewCollectionType(List<T> list, Function<T, R> converter) { //возворащаем коллекцию нового типа ё
        //  втесте на входе коллекция эмплои на выходе
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(converter.apply(t));
        }
        return result;
    }

    public <T> List<T> forAllDo(List<T> list, Function<T, T> AllDo) {
        for (T t : list) {
            AllDo.apply(t);
        }
        return list;
    }

    public <T> List<T> unmodifiableCollection(List<T> list) {
        return Collections.unmodifiableList(list);
    }


    //на вход подается коллекция и функция которая что то делает / возвращаем тот же тип данных но изменненое состояние каждого обьекта (добавляем зп)
    //unmodifilable collection метод которые делает так чтобы коллекция была неизменяемой (запращает измения);
}
