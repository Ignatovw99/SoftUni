package gamestore.commands.game;

import gamestore.commands.Executable;
import gamestore.constants.CommandValues;
import gamestore.domain.dtos.binding.AddGameDto;
import gamestore.services.api.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class AddGame implements Executable {

    private static final int TITLE_INDEX = 0;
    private static final int PRICE_INDEX = 1;
    private static final int SIZE_INDEX = 2;
    private static final int TRAILER_INDEX = 3;
    private static final int THUBNAIL_URL_INDEX = 4;
    private static final int DESCRIPTION_URL_INDEX = 5;
    private static final int RELEASE_DATE_INDEX = 6;

    private GameService gameService;

    @Autowired
    public AddGame(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public String execute(String... arguments) {
        AddGameDto addGameDto = new AddGameDto(
                arguments[TITLE_INDEX],
                new BigDecimal(arguments[PRICE_INDEX]),
                Double.parseDouble(arguments[SIZE_INDEX]),
                arguments[TRAILER_INDEX],
                arguments[THUBNAIL_URL_INDEX],
                arguments[DESCRIPTION_URL_INDEX],
                LocalDate.parse(arguments[RELEASE_DATE_INDEX], CommandValues.DATE_FORMATTER)
        );
        return this.gameService.addGame(addGameDto);
    }
}
