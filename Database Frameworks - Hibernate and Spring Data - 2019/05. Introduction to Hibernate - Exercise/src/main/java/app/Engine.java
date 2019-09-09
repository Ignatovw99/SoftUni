package app;

import constants.Messages;

import java.io.BufferedReader;
import java.io.IOException;

public class Engine implements Runnable {
    private final ApplicationManager applicationManager;
    private final BufferedReader reader;

    public Engine(ApplicationManager applicationManager, BufferedReader reader) {
        this.applicationManager = applicationManager;
        this.reader = reader;
    }

    @Override
    public void run() {
        System.out.print(Messages.CHOOSE_PROBLEM);
        try {
            int problemNumber = Integer.parseInt(this.reader.readLine());
            String output;
            switch (problemNumber) {
                case 2:
                    output = this.applicationManager.removeObjects();
                    break;
                case 3:
                    System.out.print(Messages.ENTER_EMPLOYEE_NAME);
                    String employeeName = this.reader.readLine();
                    output = this.applicationManager.containsEmployee(employeeName);
                    break;
                case 4:
                    output = this.applicationManager.employeesWithSalaryOver50000();
                    break;
                case 5:
                    output = this.applicationManager.employeesFromDepartment();
                    break;
                case 6:
                    System.out.print(Messages.ENTER_EMPLOYEE_LAST_NAME);
                    String employeeLastName = this.reader.readLine();
                    output = this.applicationManager.addNewAddressAndUpdateEmployee(employeeLastName);
                    break;
                case 7:
                    output = this.applicationManager.addressesWithEmployeeCount();
                    break;
                case 8:
                    System.out.print(Messages.ENTER_EMPLOYEE_ID);
                    int employeeId = Integer.parseInt(this.reader.readLine());
                    output = this.applicationManager.getEmployeeWithProject(employeeId);
                    break;
                case 9:
                    output = this.applicationManager.findLatestTenProjects();
                    break;
                case 10:
                    output = this.applicationManager.increaseSalaries();
                    break;
                case 11:
                    System.out.print(Messages.ENTER_TOWN_NAME);
                    String townName = this.reader.readLine();
                    output = this.applicationManager.removeTown(townName);
                    break;
                case 12:
                    System.out.print(Messages.ENTER_EMPLOYEE_FIRST_NAME_PATTERN);
                    String pattern = this.reader.readLine();
                    output = this.applicationManager.findEmployeeByFirstName(pattern);
                    break;
                case 13:
                    output = this.applicationManager.employeesMaximumSalaries();
                    break;
                    default:
                        output = Messages.INVALID_PROBLEM_NUMBER;
                        break;
            }
            System.out.print(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
