package service;

import exceptions.EmployeeAlreadyAddedException;
import exceptions.EmployeeNotFoundException;
import exceptions.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class EmployeeService {
    private static final int LIMIT = 10;
    private final List<service.Employee> employees;

    public EmployeeService(List<service.Employee> employees) {
        this.employees = new ArrayList<>();
    }


    public service.Employee addEmployee(String firstName, String lastName) {
        service.Employee employee = new service.Employee(firstName, lastName);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() < LIMIT) {
            employees.add(employee);
            return employee;
        }
        throw new EmployeeStorageIsFullException();
    }

    public service.Employee findEmployee(String firstName, String lastName) {
        service.Employee employee = new service.Employee(firstName, lastName);
        for (service.Employee value : employees) {
            if (Objects.equals(employee, value)) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException();
    }

    public service.Employee removeEmployee(String firstName, String lastName) {
        service.Employee employee = new service.Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        return employee;
    }

    public List<service.Employee> getAll() {
        return new ArrayList<>(employees);
    }
}
