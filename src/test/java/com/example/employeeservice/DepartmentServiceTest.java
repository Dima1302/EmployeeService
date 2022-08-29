package com.example.employeeservice;

import com.example.employeeservice.exceptions.EmployeeNotFoundException;
import com.example.employeeservice.service.DepartmentService;
import com.example.employeeservice.service.Employee;
import com.example.employeeservice.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;


import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {
    @Mock
    private EmployeeService employeeService;
    @InjectMocks
    private DepartmentService departmentService;

    @BeforeEach
    public void beforeEach() {
        List<Employee> employees = List.of(
                new Employee("Василий", "Иванов", 1, 40_000),
                new Employee("Виктор", "Воробьев", 1, 55_000),
                new Employee("Ольга", "Сидорчук", 1, 60_000),
                new Employee("Игорь", "Захаров", 2, 70_000),
                new Employee("Владимир", "Артемов", 2, 100_000)
        );
        when(employeeService.getAll()).thenReturn(employees);
    }

    @ParameterizedTest
    @MethodSource("employeeWithMaxSalaryParam")
    public void employeeWithMaxSalaryPositiveTest(int department,Employee expected) {
        assertThat(departmentService.maxSalary(department)).isEqualTo(expected);
    }
    @Test
    public void employeeWithMaxSalaryNegativeTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class).isThrownBy(() -> departmentService.maxSalary(3));
    }
    @ParameterizedTest
    @MethodSource("employeeWithMinSalaryParam")
    public void employeeWithMinSalaryPositiveTest(int department,Employee expected) {
        assertThat(departmentService.minSalary(department)).isEqualTo(expected);
    }

    @Test
    public void employeeWithMinSalaryNegativeTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class).isThrownBy(() -> departmentService.minSalary(3));
    }
    @ParameterizedTest
    @MethodSource("employeesFromDepartmentParams")
    public void typeAllStuffPositiveTest(int department,List<Employee>expected) {
        assertThat(departmentService.typeAllStuff(department)).containsExactlyElementsOf(expected);
    }
    @Test
    public void EmployeeNamesTest() {
        assertThat(departmentService.typeEmployeeNames()).containsAllEntriesOf(
                Map.of(1,List.of(new Employee("Василий", "Иванов", 1, 40_000)),
                        2,List.of(new Employee("Игорь", "Захаров", 2, 70_000))
        ));   }



    private static Stream<Arguments> employeeWithMaxSalaryParam() {

        return Stream.of(
                Arguments.of(1, new Employee("Иван", "Иванов", 1, 80_000)),
                Arguments.of(2, new Employee("Андрей", "Андреев", 2, 55_000))

        );
    }

    private static Stream<Arguments> employeeWithMinSalaryParam() {

        return Stream.of(
                Arguments.of(1, new Employee("Василий", "Филлипов", 1, 90_000)),
                Arguments.of(2, new Employee("Петр", "Корнеев", 2, 95_000))

        );


    }
    private static Stream<Arguments> employeesFromDepartmentParams() {

        return Stream.of(
                Arguments.of(1,List.of( new Employee("Василий", "Иванов", 1, 40_000),
                        new Employee("Виктор", "Воробьев", 1, 55_000),
                        new Employee("Ольга", "Сидорчук", 1, 60_000))),
                Arguments.of(2, new Employee("Игорь", "Захаров", 2, 70_000),
                        new Employee("Владимир", "Артемов", 2, 100_000)),
                Arguments.of(3, Collections.emptyList())

        );


    }
}




