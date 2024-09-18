package com.github.na206851.lesson5;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Function;
import java.util.function.Predicate;

public class CollectionUtils {

    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    public static <T, R> List<R> transformToNewCollection(List<T> list, Function<T, R> converter) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(converter.apply(t));
        }
        return result;
    }

    public static <T, R> List<T> transformOldCollection(List<T> list, Function<T, R> converter) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            T element = iterator.next();
            R newElement = converter.apply(element);
            iterator.set((T) newElement);
        }
        return list;
    }

    public static <T> List<T> forAllDo(List<T> list, Function<T, T> AllDo) {
        for (T t : list) {
            AllDo.apply(t);
        }
        return list;
    }

    public static <T> List<T> unmodifiableCollection(List<T> list) {
        return Collections.unmodifiableList(list);
    }
}