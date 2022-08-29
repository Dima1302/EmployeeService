package com.example.employeeservice.service;



import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class Employee {

    private final String firstName;

    private final String lastName;
    private final int department;
    private final double salary;

    public Employee(String firstname, String lastname, int department, double salary) {
        this.firstName = StringUtils.capitalize(firstname.toLowerCase());
        this.lastName = StringUtils.capitalize(lastname.toLowerCase());
        this.department = department;
        this.salary = salary;
    }

    public int getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public String getName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "ФИО сотрудника " + firstName + " " + lastName + " " + salary + " " + department + " " + ". ";

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return department == employee.department && Double.compare(employee.salary, salary) == 0 && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, department, salary);
    }
}




