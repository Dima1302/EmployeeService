package service;



import java.util.Objects;

public class Employee {

    private String firstName;

    private String lastName;

    public Employee(String firstname, String lastname) {
        this.firstName = firstname;
        this.lastName = lastname;
    }

    public String getName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "ФИО сотрудника " + firstName + " " + lastName + ". ";

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}


