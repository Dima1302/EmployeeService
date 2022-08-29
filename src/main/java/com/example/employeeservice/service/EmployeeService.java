package com.example.employeeservice.service;

import com.example.employeeservice.exceptions.CheckException;
import com.example.employeeservice.exceptions.EmployeeAlreadyAddedException;
import com.example.employeeservice.exceptions.EmployeeNotFoundException;
import com.example.employeeservice.exceptions.EmployeeStorageIsFullException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class EmployeeService {
    private static final int LIMIT = 10;
    private static final Map<String, Employee> employees = new HashMap<>();

    public static Map<String, Employee> getEmployees() {
        return employees;
    }

     public List<Employee> getAll() {
        return new ArrayList<>(employees.values());
    }

    private String getKey(String name, String surname) {
        return name + " " + surname;
    }


    public Employee add(String firstName, String lastName, double salary, int department) {
        if (!check(firstName,lastName)) {
            throw new CheckException();
        }
        Employee employee = new Employee(firstName, lastName, department, salary);
        String key = getKey(firstName, lastName);
        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() < LIMIT) {
            employees.put(key, employee);
            return employee;
        }
        throw new EmployeeStorageIsFullException();
    }


    public Employee delete(String firstName, String lastName) {
        if (!check(firstName,lastName)) {
            throw new CheckException();
        }
        String key = getKey(firstName, lastName);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException();
        }
        return employees.remove(key);
    }


    public Employee find(String firstName, String lastName) {
        if (!check(firstName,lastName)) {
            throw new CheckException();
        }
        String key = getKey(firstName, lastName);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException();
        }
        return employees.get(key);
    }



    private boolean check(String firstName, String lastName) {
        return StringUtils.isAlpha(firstName) && StringUtils.isAlpha(lastName);
    }
}


