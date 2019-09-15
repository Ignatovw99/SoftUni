package app.ccb.services;

import app.ccb.constants.Constants;
import app.ccb.constants.FilePaths;
import app.ccb.domain.dtos.CardImportRootDto;
import app.ccb.domain.entities.BankAccount;
import app.ccb.domain.entities.Card;
import app.ccb.repositories.BankAccountRepository;
import app.ccb.repositories.CardRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import app.ccb.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    private final BankAccountRepository bankAccountRepository;

    private final FileUtil fileUtil;

    private final XmlParser xmlParser;

    private final ModelMapper modelMapper;

    private final ValidationUtil validationUtil;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository, BankAccountRepository bankAccountRepository, FileUtil fileUtil, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.cardRepository = cardRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean cardsAreImported() {
        return this.cardRepository.count() != 0;
    }

    @Override
    public String readCardsXmlFile() {
        return this.fileUtil.readFile(FilePaths.CARDS_XML_FILE_PATH);
    }

    @Override
    public String importCards() {
        StringBuilder importResult = new StringBuilder();

        this.xmlParser.convertXmlToEntity(CardImportRootDto.class, FilePaths.CARDS_XML_FILE_PATH)
                .getCardImportDtos()
                .forEach(cardImportDto -> {
                    Card card = this.modelMapper.map(cardImportDto, Card.class);
                    BankAccount bankAccount = this.bankAccountRepository.findByAccountNumber(cardImportDto.getBankAccountNumber());
                    card.setBankAccount(bankAccount);
                    if (!this.validationUtil.isValid(card)) {
                        importResult.append(Constants.ERROR_MESSAGE)
                                .append(System.lineSeparator());
                        return;
                    }

                    importResult.append(String.format(Constants.SUCCESSFULLY_IMPORTED_MESSAGE,
                            String.format("Card - %s", card.getCardNumber()))
                    )
                            .append(System.lineSeparator());
                    this.cardRepository.saveAndFlush(card);
                });

        return importResult.toString().trim();
    }
}
