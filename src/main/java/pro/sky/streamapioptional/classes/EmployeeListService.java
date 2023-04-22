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
            "Роман Романюк", new Employee("Роман", "Романюк", 77777f, 2),
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
    public Employee maxSalary(int department) {
        List<Employee> employeeMinList = new ArrayList<>(emplMap.values());
        Employee userMax = employeeMinList.stream()
                .filter(e -> e.getDepartment() == department  )
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElse(null);
        return userMax;
    }
/*return employeeService.getAll().stream()
        .filter(employee -> employee.getDepartment() == department)
            .max(Comparator.comparingInt(Employee::getSalary))
            .orElse(null);*/
    public Employee minSalary(int department) {
        List<Employee> employeeMinList = new ArrayList<>(emplMap.values());
        Employee userMin = employeeMinList.stream()
                .filter(e -> e.getDepartment() == department  )
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElse(null);
        return userMin;
    }
    /*public Map<Integer, List<Employee>> findEmployeesByDepartment() {
        return employeeService.getAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }*/
    public Map<Integer, List<Employee>> all() {
        List<Employee> employeeMinList = new ArrayList<>(emplMap.values());
        Map<Integer, List<Employee>> employeeAllMap = employeeMinList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        return employeeAllMap;

    }

    public List<Employee> allDepartment(int department) {
        List<Employee> employeeMinList = new ArrayList<>(emplMap.values());
        List<Employee> listDepId = employeeMinList.stream()
        .filter(e -> e.getDepartment() == department)
                .toList();
        return listDepId;



    }

}

