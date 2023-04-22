package pro.sky.streamapioptional.classes;


import java.util.Objects;

public class Employee {


    private String firstName;
    private String lastName;
    private double salary;
    private int department;

    //private final int id;
    public static int country = 0;

    public Employee(String firstName, String lastName, double salary, int department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.department = department;
    }

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public static int getCountry() {
        return country;
    }


    @Override
    public String toString() {
        return  "Фамилия=  " + firstName +
                ", Отчество = " + lastName +
                " Зарплата= " + salary +
                "  ID  =" + department + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return firstName.equals(employee.firstName) && /*id == employee.id && */lastName.equals(employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName/*, id*/);
    }

}
