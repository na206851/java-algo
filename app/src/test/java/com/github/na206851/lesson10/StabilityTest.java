package com.github.na206851.lesson10;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

public class StabilityTest {

    @Test
    public void testEmployee() {
        Employee employee1 = new Employee("Name1", 1);
        Employee employee2 = new Employee("Name2", 2);
        Employee employee3 = new Employee("Name3", 3);
        Employee employee4 = new Employee("Name4", 4);

        Consumer<Sort<Employee>> SortConsumer = (Sort<Employee> test) -> {
            Employee[] exp = new Employee[]{employee1, employee2, employee3, employee4};
            Employee[] act = new Employee[]{employee4, employee3, employee2, employee1};

            Assertions.assertArrayEquals(exp, test.sort(act));
        };
        SortConsumer.accept(new BubbleSort<>());
        SortConsumer.accept(new InsertionSort<>());
        SortConsumer.accept(new QuickSort<>());
        SortConsumer.accept(new MergeSort<>());
    }

    @Test
    public void testStability() {
        Employee employee1 = new Employee("Name1", 1);
        Employee employee2 = new Employee("Name2", 1);
        Employee employee3 = new Employee("Name3", 3);
        Employee employee4 = new Employee("Name4", 4);

        Consumer<Sort<Employee>> SortConsumer = (Sort<Employee> test) -> {
            Employee[] exp = new Employee[]{employee1, employee2, employee3, employee4};
            Employee[] act = new Employee[]{employee3, employee1, employee2, employee4};

            Assertions.assertArrayEquals(exp, test.sort(act));
        };
        SortConsumer.accept(new BubbleSort<>());
        SortConsumer.accept(new InsertionSort<>());
        SortConsumer.accept(new QuickSort<>());
        SortConsumer.accept(new MergeSort<>()); //не стабильна (меняет местами элементы с одним ключем)
    }

    @Test
    public void testStability1() {
        Employee employee1 = new Employee("Name1", 1);
        Employee employee2 = new Employee("Name2", 1);
        Employee employee3 = new Employee("Name3", 3);
        Employee employee4 = new Employee("Name4", 4);

        Consumer<Sort<Employee>> SortConsumer = (Sort<Employee> test) -> {
            Employee[] exp = new Employee[]{employee1, employee2, employee3, employee4};
            Employee[] act = new Employee[]{employee3, employee1, employee2, employee4};

            Assertions.assertArrayEquals(exp, test.sort(act));
        };
        SortConsumer.accept(new QuickSort<>());
        SortConsumer.accept(new MergeSort<>());
    }

}
