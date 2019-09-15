package app.ccb.services;

import app.ccb.constants.Constants;
import app.ccb.constants.FilePaths;
import app.ccb.domain.dtos.ClientImportDto;
import app.ccb.domain.entities.Client;
import app.ccb.domain.entities.Employee;
import app.ccb.repositories.ClientRepository;
import app.ccb.repositories.EmployeeRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final EmployeeRepository employeeRepository;

    private final FileUtil fileUtil;

    private final Gson gson;

    private final ModelMapper modelMapper;

    private final ValidationUtil validationUtil;
    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, EmployeeRepository employeeRepository, FileUtil fileUtil, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean clientsAreImported() {
        return this.clientRepository.count() != 0;
    }

    @Override
    public String readClientsJsonFile() {
        return this.fileUtil.readFile(FilePaths.CLIENTS_JSON_FILE_PATH);
    }

    @Override
    public String importClients(String clients) {
        StringBuilder importResult = new StringBuilder();

        Arrays.stream(this.gson.fromJson(clients, ClientImportDto[].class))
                .forEach(clientImportDto -> {
                    Client client = this.modelMapper.map(clientImportDto, Client.class);

                    if (this.clientRepository.existsByFullName(client.getFullName())) {
                        importResult.append(Constants.DUPLICATE_DATA_MESSAGE)
                                .append(System.lineSeparator());
                        return;
                    }

                    String[] employeeNames = clientImportDto.getEmployee().split("\\s+");
                    Employee employee = this.employeeRepository.findByFirstNameAndLastName(employeeNames[0], employeeNames[1]);

                    if (employee == null || !this.validationUtil.isValid(client)) {
                        importResult.append(Constants.ERROR_MESSAGE)
                                .append(System.lineSeparator());
                        return;
                    }

                    employee.getClients().add(client);
                    this.clientRepository.saveAndFlush(client);

                    importResult.append(String.format(Constants.SUCCESSFULLY_IMPORTED_MESSAGE,
                            String.format("Client - %s", client.getFullName()))
                    )
                            .append(System.lineSeparator());
                });

        return importResult.toString().trim();
    }

    @Override
    public String exportFamilyGuy() {
        StringBuilder exportResult = new StringBuilder();
        Client familyGuy = this.clientRepository.findFamilyGuy().get(0);

        exportResult.append("Full name: ").append(familyGuy.getFullName()).append(System.lineSeparator())
                .append("Age: ").append(familyGuy.getAge()).append(System.lineSeparator())
                .append("Bank Account: ").append(familyGuy.getBankAccount().getAccountNumber()).append(System.lineSeparator());

        familyGuy.getBankAccount().getCards()
                .forEach(card ->
                        exportResult.append("\tCard Number: ").append(card.getCardNumber()).append(System.lineSeparator())
                        .append("\tCard Status: ").append(card.getCardStatus()).append(System.lineSeparator())
                );

        return exportResult.toString().trim();
    }
}
