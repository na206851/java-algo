package com.github.na206851.lesson5;

public class Employee {
    String firstName;
    String lastName;
    int age;
    int workExperience;
    int salary;

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public int getAge() {
        return this.age;
    }

    public int getWorkExperience() {
        return this.workExperience;
    }

    public int getSalary() {
        return this.salary;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Employee() {
    }

    public Employee(String firstName) {
        this.firstName = firstName;
    }

    public Employee(String firstName, int salary) {
        this.firstName = firstName;
        this.salary = salary;
    }

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Employee(String firstName, String lastName, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    public Employee(String firstName, String lastName, int age, int workExperience, int wages) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.workExperience = workExperience;
        this.salary = wages;
    }

    @Override
    public int hashCode() {
        return lastName.hashCode();
    }

    @Override
    public boolean equals(Object em) {
        if (em == this) {
            return true;
        }
        if (em == null || em.getClass() != this.getClass()) {
            return false;
        }
        Employee c = (Employee) em;
        return CharSequence.compare(firstName, c.firstName) == 0
                || CharSequence.compare(lastName, c.lastName) == 0;
    }

    public boolean contains(String filterSearch) {
        return firstName.contains(filterSearch) || lastName.contains(filterSearch);
    }

    @Override
    public String toString() {
        return this.getFirstName() + " " + this.getSalary();
    }
}