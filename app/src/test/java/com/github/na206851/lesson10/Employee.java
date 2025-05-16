package com.github.na206851.lesson10;

public class Employee implements Comparable<Employee> {
    String name;
    int key;

    public Employee(String name, int number) {
        this.name = name;
        this.key = number;
    }

    @Override
    public int compareTo(Employee o) {
        return Integer.compare(this.key, o.key);
    }

    @Override
    public String toString() {
        return name + " " + key;
    }
}
