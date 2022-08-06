package controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.DepartmentService;
import service.Employee;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departaments")

public class DepartamentController {


    private final DepartmentService departamentService;

    public DepartamentController(DepartmentService departamentService) {
        this.departamentService = departamentService;
    }

    @GetMapping("/max-salary")
    public Employee maxSalary(@RequestParam("departamentId") int department) {
        return departamentService.maxSalary(department);
    }

    @GetMapping("/min-salary")
    public Employee minSalary(@RequestParam("departamentId") int department) {
        return departamentService.minSalary(department);
    }

    @GetMapping(value = "/all",params = "departamentId")
    public List<Employee> typeAllStuff(@RequestParam("departament") int department) {
        return departamentService.typeAllStuff(department);
    }
    @GetMapping("/all")
    public Map<Integer,List<Employee>> typeEmployeeNames() {
        return departamentService.typeEmployeeNames();
    }
}

