package companyroster;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Department> departments = new HashMap<>();

        int employeesCount = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < employeesCount; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            String name = tokens[0];
            double salary = Double.parseDouble(tokens[1]);
            String departmentName = tokens[3];

            Employee employee;
            switch (tokens.length) {
                case 4:
                    employee = new Employee(name, salary);
                    break;
                case 5:
                    if (isEmail(tokens[4])) {
                        employee = new Employee(name, salary, tokens[4]);
                    } else {
                        employee = new Employee(name, salary, Integer.parseInt(tokens[4]));
                    }
                    break;
                default:
                    employee = new Employee(name, salary, tokens[4], Integer.parseInt(tokens[5]));
                    break;
            }

            if (!departments.containsKey(departmentName)) {
                departments.put(departmentName, new Department(departmentName));
            }

            departments.get(departmentName).getEmployees().add(employee);
        }

        Map.Entry<String, Department> departmentWithHighestAverageSalary = departments.entrySet().stream()
                .sorted((first, second) -> Double.compare(second.getValue().getAverageSalary(), first.getValue().getAverageSalary()))
                .findFirst()
                .get();

        System.out.println(String.format("Highest Average Salary: %s", departmentWithHighestAverageSalary.getKey()));

        departmentWithHighestAverageSalary.getValue().getEmployees().stream()
                .sorted((first, second) -> Double.compare(second.getSalary(), first.getSalary()))
                .forEach(employee -> System.out.println(employee.getInfo()));
    }

    private static boolean isEmail(String token) { return token.contains("@"); }
}
