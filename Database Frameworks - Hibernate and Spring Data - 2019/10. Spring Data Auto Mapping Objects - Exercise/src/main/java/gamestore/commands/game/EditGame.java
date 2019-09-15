package gamestore.commands.game;

import gamestore.commands.Executable;
import gamestore.constants.CommandMessages;
import gamestore.domain.dtos.binding.EditGameDto;
import gamestore.services.api.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class EditGame implements Executable {

    private static final String ARGUMENT_SEPARATOR = "=";
    private static final int ID_INDEX = 0;
    private static final int PARAM_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    private GameService gameService;

    @Autowired
    public EditGame(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public String execute(String... arguments) {
        long id = Long.parseLong(arguments[ID_INDEX]);
        EditGameDto editGameDto = this.gameService.getGameToEditById(id);

        if (editGameDto == null) {
            return String.format(CommandMessages.NO_SUCH_GAME_FOUND, id);
        }

        for (int i = 1; i < arguments.length; i++) {
            String[] tokens = arguments[i].split(ARGUMENT_SEPARATOR);

            switch (tokens[PARAM_INDEX]) {
                case "title":
                    editGameDto.setTitle(tokens[VALUE_INDEX]);
                    break;
                case "price":
                    editGameDto.setPrice(new BigDecimal(tokens[VALUE_INDEX]));
                    break;
                case "size":
                    editGameDto.setSize(Double.parseDouble(tokens[VALUE_INDEX]));
                    break;
                case "thumbnail":
                    editGameDto.setThumbnailURL(tokens[VALUE_INDEX]);
                    break;
                case "trailer":
                    editGameDto.setTrailer(tokens[VALUE_INDEX]);
                    break;
                case "description":
                    editGameDto.setDescription(tokens[VALUE_INDEX]);
                    break;
                case "date":
                    editGameDto.setReleaseDate(LocalDate.parse(tokens[VALUE_INDEX]));
                    break;
            }
        }
        return this.gameService.updateGame(editGameDto);
    }
}
