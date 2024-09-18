package com.github.na206851.lesson5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

class CollectionUtilsTest {

    @Test
    void filterTest() {
        List<Employee> actual1 = List.of(new Employee("name1"), new Employee("nikita"), new Employee("amur"));
        List<Employee> expected1 = List.of(new Employee("name1"));
        Assertions.assertIterableEquals(expected1, CollectionUtils.filter(actual1, em -> em.getFirstName().contains("nam")));

        List<Employee> actual2 = List.of(new Employee("name1"), new Employee("nikita"), new Employee("amur"));
        List<Employee> expected2 = List.of(new Employee("amur"));
        Assertions.assertIterableEquals(expected2, CollectionUtils.filter(actual2, em -> em.getFirstName().contains("amur")));

        List<Integer> listNums = List.of(1, 2, 3, 4, 5);
        List<Integer> expectedNums = List.of(1, 3, 5);
        Assertions.assertIterableEquals(expectedNums, CollectionUtils.filter(listNums, em -> em % 2 != 0));
    }

    @Test
    void transformCollectionType() {
        List<Employee> employees1 = List.of(new Employee("one"), new Employee("two"), new Employee("three"),
                new Employee("four"), new Employee("five"));

        Function<Employee, String> employeeStringFunction1 = employee -> employee.toString();
        List<String> expected1 = List.of("one 0", "two 0", "three 0", "four 0", "five 0");
        Assertions.assertIterableEquals(expected1, CollectionUtils.transformToNewCollection(employees1, employeeStringFunction1));

        Function<Number, String> StringConvertToInteger = String::valueOf;
        List<Number> actual = List.of(1, 2, 3, 4, 5);
        List<String> expected3 = List.of("1", "2", "3", "4", "5");
        Assertions.assertIterableEquals(expected3, CollectionUtils.transformToNewCollection(actual, StringConvertToInteger));
    }

    @Test
    public void transformOldCollectionToNewType() {
        Employee one = new Employee("one");
        List<Employee> employees = new ArrayList<>();
        employees.add(one);

        List<String> expected = new ArrayList<>();
        expected.add("one 0");

        Function<Employee, String> transformOldCollection = (employee) -> employee.toString();

        Assertions.assertIterableEquals(expected, CollectionUtils.transformOldCollection(employees, transformOldCollection));
    }

    @Test
    public void testForAllDo() {
        Employee one = new Employee("one", 1);
        Employee two = new Employee("two", 10);
        Employee three = new Employee("three", 100);
        List<Employee> employeeList = List.of(one, two, three);

        int raise = 1;
        Function<Employee, Employee> increaseSalary = employee -> {
            employee.setSalary(employee.getSalary() + raise);
            return employee;
        };
        Assertions.assertIterableEquals(CollectionUtils.forAllDo(employeeList, increaseSalary),
                List.of(new Employee("one", 2), new Employee("two", 11), new Employee("three", 101)));
    }

    @Test
    public void testUnmodifiableList() {
        List<Employee> list = new ArrayList<>();
        list.add(new Employee("one", 1));

        Collection<Employee> employees = CollectionUtils.unmodifiableCollection(list);
        Assertions.assertThrows(UnsupportedOperationException.class, () -> employees.add(new Employee("two", 2)));
        Assertions.assertThrows(UnsupportedOperationException.class, () -> employees.remove("one"));
    }
}