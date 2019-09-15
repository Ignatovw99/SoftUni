package app.ccb.services;

import app.ccb.constants.Constants;
import app.ccb.constants.FilePaths;
import app.ccb.domain.dtos.BankAccountImportRootDto;
import app.ccb.domain.entities.BankAccount;
import app.ccb.domain.entities.Client;
import app.ccb.repositories.BankAccountRepository;
import app.ccb.repositories.ClientRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import app.ccb.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    private final ClientRepository clientRepository;

    private final FileUtil fileUtil;

    private final XmlParser xmlParser;

    private final ModelMapper modelMapper;

    private final ValidationUtil validationUtil;

    @Autowired
    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository, ClientRepository clientRepository, FileUtil fileUtil, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.bankAccountRepository = bankAccountRepository;
        this.clientRepository = clientRepository;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean bankAccountsAreImported() {
        return this.bankAccountRepository.count() != 0;
    }

    @Override
    public String readBankAccountsXmlFile() {
        return this.fileUtil.readFile(FilePaths.BANK_ACCOUNTS_XML_FILE_PATH);
    }

    @Override
    public String importBankAccounts() {
        StringBuilder importResult = new StringBuilder();

        this.xmlParser.convertXmlToEntity(BankAccountImportRootDto.class, FilePaths.BANK_ACCOUNTS_XML_FILE_PATH)
                .getBankAccountImportDtos()
                .forEach(bankAccountImportDto -> {
                    if (this.bankAccountRepository.existsByAccountNumber(bankAccountImportDto.getAccountNumber())) {
                        importResult.append(Constants.DUPLICATE_DATA_MESSAGE)
                                .append(System.lineSeparator());
                        return;
                    }

                    BankAccount bankAccount = this.modelMapper.map(bankAccountImportDto, BankAccount.class);
                    Client client = this.clientRepository.findByFullName(bankAccountImportDto.getClientFullName());

                    if (client == null || !this.validationUtil.isValid(bankAccount)) {
                        importResult.append(Constants.ERROR_MESSAGE)
                                .append(System.lineSeparator());
                        return;
                    }

                    client.setBankAccount(bankAccount);
                    bankAccount.setClient(client);

                    importResult.append(String.format(Constants.SUCCESSFULLY_IMPORTED_MESSAGE,
                            String.format("BankAccount - %s", bankAccount.getAccountNumber()))
                    )
                            .append(System.lineSeparator());
                    this.bankAccountRepository.saveAndFlush(bankAccount);
                });

        return importResult.toString().trim();
    }
}
