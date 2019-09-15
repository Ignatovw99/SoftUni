package softuni.workshop.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.workshop.domain.dtos.binding.EmployeeRootDto;
import softuni.workshop.domain.dtos.view.EmployeeExportDto;
import softuni.workshop.domain.entities.Employee;
import softuni.workshop.domain.entities.Project;
import softuni.workshop.repository.EmployeeRepository;
import softuni.workshop.repository.ProjectRepository;
import softuni.workshop.util.FileUtil;
import softuni.workshop.util.JsonParser;
import softuni.workshop.util.ValidatorUtil;
import softuni.workshop.util.XmlParser;

import javax.validation.ConstraintViolation;
import javax.xml.bind.JAXBException;

import java.util.List;
import java.util.stream.Collectors;

import static softuni.workshop.constant.FilesPaths.EMPLOYEES_JSON_PATH;
import static softuni.workshop.constant.FilesPaths.EMPLOYEES_XML_PATH;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final ProjectRepository projectRepository;

    private final ModelMapper modelMapper;

    private final XmlParser xmlParser;

    private final JsonParser jsonParser;

    private final FileUtil fileUtil;

    private final ValidatorUtil validatorUtil;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ProjectRepository projectRepository, ModelMapper modelMapper, XmlParser xmlParser, JsonParser jsonParser, FileUtil fileUtil, ValidatorUtil validatorUtil) {
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.jsonParser = jsonParser;
        this.fileUtil = fileUtil;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public void importEmployees() {
        StringBuilder importConstraintViolations = new StringBuilder();
        try {
            this.xmlParser.convertXmlToObject(EmployeeRootDto.class, EMPLOYEES_XML_PATH)
                    .getEmployeeImportDtos()
                    .stream()
                    .map(employeeImportDto -> this.modelMapper.map(employeeImportDto, Employee.class))
                    .forEach(employee -> {
                        if (!this.validatorUtil.isValid(employee)) {
                            this.validatorUtil.validate(employee)
                                    .stream()
                                    .map(ConstraintViolation::getMessage)
                                    .forEach(importConstraintViolations::append);
                        } else {
                            Project project = this.projectRepository.findByName(employee.getProject().getName());
                            employee.setProject(project);
                            this.employeeRepository.saveAndFlush(employee);
                        }
                    });
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        System.out.println(importConstraintViolations);
    }


    @Override
    public boolean areImported() {
        return this.employeeRepository.count() > 0L;
    }

    @Override
    public boolean areExported() {
        return this.readEmployeesJsonFile().length() > 0;
    }

    @Override
    public String readEmployeesXmlFile() {
        return this.fileUtil.readFile(EMPLOYEES_XML_PATH);
    }

    @Override
    public String exportEmployeesWithAgeAbove() {
        StringBuilder stringBuilder = new StringBuilder();

        this.employeeRepository.findAllByAgeGreaterThan(25)
                .forEach(employee ->
                        stringBuilder.append(String.format("Name: %s %s", employee.getFirstName(), employee.getLastName())).append(System.lineSeparator())
                                .append(String.format("    Age: %s", employee.getAge())).append(System.lineSeparator())
                                .append(String.format("    Project name: %s", employee.getProject().getName())).append(System.lineSeparator())
                );

        return stringBuilder.toString().trim();
    }

    @Override
    public String readEmployeesJsonFile() {
        return this.fileUtil.readFile(EMPLOYEES_JSON_PATH);
    }

    @Override
    public String exportEmployeesWithGivenName() {
        StringBuilder stringBuilder = new StringBuilder();

        this.employeeRepository.findAllByFirstNameOrderById("Ivan")
                .forEach(employee ->
                        stringBuilder.append(String.format("Name: %s %s", employee.getFirstName(), employee.getLastName())).append(System.lineSeparator())
                                .append(String.format("\tAge: %s", employee.getAge())).append(System.lineSeparator())
                );

        return stringBuilder.toString().trim();
    }

    @Override
    public String exportEmployeesWithGivenProject() {
        StringBuilder stringBuilder = new StringBuilder();

        this.employeeRepository.findAllByProjectName("HoodWars")
                .forEach(employee ->
                        stringBuilder.append(String.format("Name: %s %s", employee.getFirstName(), employee.getLastName())).append(System.lineSeparator())
                                .append(String.format("\tAge: %s", employee.getAge())).append(System.lineSeparator())
                                .append(String.format("\tProject name: %s", employee.getProject().getName())).append(System.lineSeparator())
                );

        return stringBuilder.toString().trim();
    }

    @Override
    public void exportEmployeesToJson() {
        List<EmployeeExportDto> employeeExportDtos = this.employeeRepository.findAll()
                .stream()
                .map(employee -> this.modelMapper.map(employee, EmployeeExportDto.class))
                .collect(Collectors.toList());

        this.fileUtil.writeToFile(
                this.jsonParser.convertEntityToJson(employeeExportDtos),
                EMPLOYEES_JSON_PATH
        );
    }
}
