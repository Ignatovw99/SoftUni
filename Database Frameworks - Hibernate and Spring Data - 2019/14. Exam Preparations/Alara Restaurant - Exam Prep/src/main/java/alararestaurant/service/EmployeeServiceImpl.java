package alararestaurant.service;

import alararestaurant.domain.dtos.EmployeeImportDto;
import alararestaurant.domain.entities.Employee;
import alararestaurant.domain.entities.Position;
import alararestaurant.repository.EmployeeRepository;
import alararestaurant.repository.PositionRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import static alararestaurant.constant.FilesPaths.EMPLOYEES_IMPORT_FILE;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final PositionRepository positionRepository;

    private final FileUtil fileUtil;

    private final Gson gson;

    private final ModelMapper modelMapper;

    private final ValidationUtil validationUtil;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, PositionRepository positionRepository, FileUtil fileUtil, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.employeeRepository = employeeRepository;
        this.positionRepository = positionRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean employeesAreImported() {
        return this.employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesJsonFile() {
        return this.fileUtil.readFile(EMPLOYEES_IMPORT_FILE);
    }

    @Override
    public String importEmployees(String employees) {
        StringBuilder stringBuilder = new StringBuilder();
        Arrays.stream(this.gson.fromJson(employees, EmployeeImportDto[].class))
                .forEach(employeeImportDto -> {
                    Employee employee = this.modelMapper.map(employeeImportDto, Employee.class);
                    if (!this.validationUtil.isValid(employee)) {
                        stringBuilder.append("Invalid data format.")
                                .append(System.lineSeparator());
                    } else {
                        Position position = this.positionRepository.findByName(employeeImportDto.getPosition());
                        boolean isPositionValid = true;
                        if (position == null) {
                            position = new Position();
                            position.setName(employeeImportDto.getPosition());
                            isPositionValid = this.validationUtil.isValid(position);
                            if (!isPositionValid) {
                                stringBuilder.append("Invalid data format.")
                                        .append(System.lineSeparator());
                            } else {
                                position = this.positionRepository.saveAndFlush(position);
                            }
                        }
                        if (isPositionValid) {
                            employee.setPosition(position);
                            this.employeeRepository.saveAndFlush(employee);
                            stringBuilder.append(String.format("Record %s successfully imported.", employee.getName()))
                                    .append(System.lineSeparator());
                        }
                    }
                });
        return stringBuilder.toString().trim();
    }
}
