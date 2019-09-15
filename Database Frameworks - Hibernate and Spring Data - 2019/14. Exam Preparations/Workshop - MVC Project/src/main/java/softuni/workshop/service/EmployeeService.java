package softuni.workshop.service;

public interface EmployeeService {

    void importEmployees();

    boolean areImported();

    boolean areExported();

    String readEmployeesXmlFile();

    String exportEmployeesWithAgeAbove();

    String readEmployeesJsonFile();

    String exportEmployeesWithGivenName();

    String exportEmployeesWithGivenProject();

    void exportEmployeesToJson();
}
