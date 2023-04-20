package pro.sky.streamapioptional.classes;

import org.springframework.stereotype.Service;
import pro.sky.streamapioptional.classes.Excertions.EmployeeAlreadyAddedException;
import pro.sky.streamapioptional.classes.Excertions.EmployeeNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeListService {

    Map<String, Employee> emplMap = new HashMap(Map.of(
            "Вера Койнова", new Employee("Вера", "Койнова", 45000f, 1),
            "Виктор Сидоров", new Employee("Виктор", "Сидоров", 55000f, 3),
            "Серьгей Сидоров", new Employee("Серьгей", "Сидоров", 40000f, 1),
            "Антон Сидоров", new Employee("Антон", "Сидоров", 50000f, 3),
            "Семён Фёдоров", new Employee("Семён", "Фёдоров", 70000f, 1),
            "Роман Романюк", new Employee("Роман", "Романюк", 55555f, 2),
            "Светлана Фёдорова", new Employee("Светлана", "Фёдорова", 45000f, 2)
    ));



    public Employee addEmpl(String firstName, String lastName, String salary, String department) {
        Employee employee = new Employee(firstName, lastName, Double.parseDouble(salary), Integer.parseInt(department));
        if ((emplMap.containsKey(firstName + " " + lastName))) {
            throw new EmployeeAlreadyAddedException();
        }
        emplMap.put(firstName + " " + lastName, employee);
        return employee;
    }

    public String removeEmpl(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee employee = new Employee(firstName, lastName);
        if (!(emplMap.containsKey(firstName + " " + lastName))) {
            throw new EmployeeNotFoundException();
        }
        emplMap.remove(firstName + " " + lastName);
        return employee.toString();
    }

    public String findEmpl(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee employee = new Employee(firstName, lastName);
        for (int i = 0; i < emplMap.size(); i++) {
            if ((emplMap.containsKey(firstName + " " + lastName))) {
                return employee.toString();
            }
        }
        throw new EmployeeNotFoundException();
    }

    //расчет максимальной зарплаты
    public Employee maxSalary(String department) {
        List<Employee> employeeMaxList = new ArrayList<>(emplMap.values());
        for (int i = 0; i < employeeMaxList.size(); i++) {
            if (employeeMaxList.get(i).getDepartment() != Integer.parseInt(department)) {
                employeeMaxList.remove(i);
                i = i - 1;
            }
        }
        Employee userMax = employeeMaxList.stream()
                .max(Comparator.comparingDouble(Employee::getSalary))
                .get();

        return userMax;
    }

    public Employee minSalary(String department) {
        List<Employee> employeeMinList = new ArrayList<>(emplMap.values());
        for (int i = 0; i < employeeMinList.size(); i++) {
            if (employeeMinList.get(i).getDepartment() != Integer.parseInt(department)) {
                employeeMinList.remove(i);
                i = i - 1;
            }
        }
        Employee userMin = employeeMinList.stream()
                .min(Comparator.comparingDouble(Employee::getSalary))
                .get();

        return userMin;
    }

    public List<Employee> all() {
        List<Employee> employeeAllList = new ArrayList<>(emplMap.values());
        employeeAllList = employeeAllList.stream()
                .sorted(Comparator.comparing(Employee::getDepartment))
                .collect(Collectors.toList());

        return employeeAllList;

    }

    public List<Employee> allDepartment(String department) {
        List<Employee> employeeAllDepartmentList = new ArrayList<>(emplMap.values());
        for (int i = 0; i < employeeAllDepartmentList.size(); i++) {
            if (employeeAllDepartmentList.get(i).getDepartment() != Integer.parseInt(department)) {
                employeeAllDepartmentList.remove(i);
                i = i - 1;
            }
        }
        return employeeAllDepartmentList;



    }

}

