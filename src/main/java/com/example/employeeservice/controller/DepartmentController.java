package com.example.employeeservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.employeeservice.service.DepartmentService;
import com.example.employeeservice.service.Employee;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")

public class DepartmentController {


    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee maxSalary(@RequestParam("departmentId") int department) {
        return departmentService.maxSalary(department);
    }

    @GetMapping("/min-salary")
    public Employee minSalary(@RequestParam("departmentId") int department) {
        return departmentService.minSalary(department);
    }

    @GetMapping(value = "/all",params = "departmentId")
    public List<Employee> typeAllStuff(@RequestParam("department") int department) {
        return departmentService.typeAllStuff(department);
    }
    @GetMapping("/all")
    public Map<Integer,List<Employee>> typeEmployeeNames() {
        return departmentService.typeEmployeeNames();
    }
}

