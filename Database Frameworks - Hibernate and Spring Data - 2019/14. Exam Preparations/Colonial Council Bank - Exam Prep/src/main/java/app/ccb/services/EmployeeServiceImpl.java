package app.ccb.services;

import app.ccb.constants.Constants;
import app.ccb.constants.FilePaths;
import app.ccb.domain.dtos.EmployeeImportDto;
import app.ccb.domain.entities.Branch;
import app.ccb.domain.entities.Employee;
import app.ccb.repositories.BranchRepository;
import app.ccb.repositories.EmployeeRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final BranchRepository branchRepository;

    private final FileUtil fileUtil;

    private final Gson gson;

    private final ModelMapper modelMapper;

    private final ValidationUtil validationUtil;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, BranchRepository branchRepository, FileUtil fileUtil, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.employeeRepository = employeeRepository;
        this.branchRepository = branchRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean employeesAreImported() {
        return this.employeeRepository.count() != 0;
    }

    @Override
    public String readEmployeesJsonFile() {
        return this.fileUtil.readFile(FilePaths.EMPLOYEES_JSON_FILE_PATH);
    }

    @Override
    public String importEmployees(String employees) {
        StringBuilder importResult = new StringBuilder();

        Arrays.stream(this.gson.fromJson(employees, EmployeeImportDto[].class))
                .map(employeeImportDto -> this.modelMapper.map(employeeImportDto, Employee.class))
                .forEach(employee -> {
                    Branch branch = this.branchRepository.findByName(employee.getBranch().getName());
                    employee.setBranch(branch);
                    if (!this.validationUtil.isValid(employee)) {
                        importResult.append(Constants.ERROR_MESSAGE)
                                .append(System.lineSeparator());
                        return;
                    }

                    importResult.append(String.format(Constants.SUCCESSFULLY_IMPORTED_MESSAGE,
                            String.format("Employee - %s %s", employee.getFirstName(), employee.getLastName()))
                    )
                            .append(System.lineSeparator());
                    this.employeeRepository.saveAndFlush(employee);
                });

        return importResult.toString().trim();
    }

    @Override
    public String exportTopEmployees() {
        StringBuilder exportResult = new StringBuilder();

        this.employeeRepository.findTopEmployees()
                .forEach(employee -> {
                    exportResult.append(String.format("Full Name: %s %s", employee.getFirstName(), employee.getLastName())).append(System.lineSeparator())
                            .append("Salary: ").append(employee.getSalary()).append(System.lineSeparator())
                            .append("Started On: ").append(employee.getStartedOn()).append(System.lineSeparator())
                            .append("Clients: ").append(System.lineSeparator());

                    employee.getClients()
                            .forEach(client ->
                                    exportResult.append("\t").append(client.getFullName())
                                    .append(System.lineSeparator())
                            );
                });

        return exportResult.toString().trim();
    }
}
