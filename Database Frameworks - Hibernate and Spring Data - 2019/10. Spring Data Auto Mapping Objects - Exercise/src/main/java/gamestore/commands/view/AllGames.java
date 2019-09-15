package gamestore.commands.view;

import gamestore.commands.Executable;
import gamestore.services.api.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AllGames implements Executable {

    private GameService gameService;

    @Autowired
    public AllGames(GameService gameService) {
        this.gameService = gameService;
    }


    @Override
    public String execute(String... arguments) {
        StringBuilder builder = new StringBuilder();

        this.gameService.getAllGamesTitleAndPrice()
                .forEach(gameWithTitleAndPrice ->
                        builder.append(gameWithTitleAndPrice.toString())
                        .append(System.lineSeparator())
                );

        return builder.toString().trim();
    }
}
