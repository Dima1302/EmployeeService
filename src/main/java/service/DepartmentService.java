package service;

import exceptions.EmployeeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;
    private static final Map<String, Employee> employees = new HashMap<>();


    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    public Employee maxSalary(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);


    }

    public Employee minSalary(int department) {
        return employeeService.getAll().stream().filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> typeAllStuff(int department) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartment() == department).collect(Collectors.toList());

    }

    public Map<Integer, List<Employee>> typeEmployeeNames() {
        return employeeService.getAll().stream().collect(Collectors.groupingBy(Employee::getDepartment));

    }
}






