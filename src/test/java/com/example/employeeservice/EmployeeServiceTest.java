package com.example.employeeservice;

import com.example.employeeservice.exceptions.EmployeeAlreadyAddedException;
import com.example.employeeservice.exceptions.EmployeeNotFoundException;
import com.example.employeeservice.service.Employee;
import com.example.employeeservice.service.EmployeeService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

class EmployeeServiceTest {
    EmployeeService employeeService = new EmployeeService();

    @ParameterizedTest
    @MethodSource("Params")
    public void addNegativeTest1(String firstname, String lastname, int department, double salary) {
        Employee expected = new Employee(firstname, lastname, department, salary);
        assertThat(employeeService.add(firstname, lastname, department, (int) salary)).isEqualTo(expected);
        assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(() -> employeeService.add(firstname, lastname, department, (int) salary));
    }

    @ParameterizedTest
    @MethodSource("Params")
    public void addNegativeTest2(String firstname, String lastname, int department, double salary) {
        List<Employee> employees = generateEmployees(10);
        employees.forEach(employee ->
                assertThat(employeeService.add(employee.getName(),employee.getLastName(),employee.getDepartment(), (int) employee.getSalary())).isEqualTo(employee)
        );

        assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(() -> employeeService.add(firstname, lastname, department, (int) salary));
    }


    @ParameterizedTest
    @MethodSource("Params")
    public void deleteNegativeTest(String firstname, String lastname, int department, double salary) {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.delete("test", "test"));

        Employee expected = new Employee(firstname, lastname, department, salary);
        assertThat(employeeService.add(firstname, lastname, department, (int) salary)).isEqualTo(expected);
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.delete("test", "test"));
    }

    @ParameterizedTest
    @MethodSource("Params")
    public void deletePositiveTest(String firstname, String lastname, int department, double salary) {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.delete("test", "test"));

        Employee expected = new Employee(firstname, lastname, department, salary);
        assertThat(employeeService.add(firstname, lastname, department, (int) salary)).isEqualTo(expected);

        assertThat(employeeService.delete(firstname, lastname)).isEqualTo(expected);
        assertThat(employeeService.getAll().isEmpty());
    }

    @ParameterizedTest
    @MethodSource("Params")
    public void findNegativeTest(String firstname, String lastname, int department, double salary) {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.find("test", "test"));

        Employee expected = new Employee(firstname, lastname, department, salary);
        assertThat(employeeService.add(firstname, lastname, department, (int) salary)).isEqualTo(expected);
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.find("test", "test"));

    }

    @ParameterizedTest
    @MethodSource("Params")
    public void findPositiveTest(String firstname, String lastname, int department, double salary) {
        Employee expected = new Employee(firstname, lastname, department, salary);
        assertThat(employeeService.add(firstname, lastname, department, (int) salary)).isEqualTo(expected);

        assertThat(employeeService.find(firstname, lastname)).isEqualTo(expected);
        assertThat(employeeService.getAll()).hasSize(1);

    }

    private List<Employee> generateEmployees(int size) {
        return Stream.iterate(1, i -> i + 1)
                .limit(size)
                .map(i -> new Employee("Name" + (char) ((int) 'a' + i), "LastName" + (char) ((int) 'a' + i), i, 10_000 + i))
                .collect(Collectors.toList());
    }

    private static Stream<Arguments> Params() {

        return Stream.of(
                Arguments.of("Ivan", "Ivanov", 1, 100_000),
                Arguments.of("Petr", "Petrov", 1, 80_000),
                Arguments.of("Sergey", "Sergeev", 2, 50_000));

    }
}



