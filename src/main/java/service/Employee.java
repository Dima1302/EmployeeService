package service;



import java.util.Objects;

public class Employee {

    private String firstName;

    private String lastName;
    private final int departament;
    private final double salary;

    public Employee(String firstname, String lastname, int departament, double salary) {
        this.firstName = firstname;
        this.lastName = lastname;
        this.departament = departament;
        this.salary = salary;
    }

    public int getDepartament() {
        return departament;
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
        return "ФИО сотрудника " + firstName + " " + lastName + " " + salary + " " + departament + " " + ". ";

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return departament == employee.departament && Double.compare(employee.salary, salary) == 0 && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, departament, salary);
    }
}




