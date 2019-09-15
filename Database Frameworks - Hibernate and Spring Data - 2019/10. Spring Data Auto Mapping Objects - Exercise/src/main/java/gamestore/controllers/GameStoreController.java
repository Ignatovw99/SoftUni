package gamestore.controllers;

import gamestore.commands.CommandFactory;
import gamestore.commands.Executable;
import gamestore.constants.CommandMessages;
import gamestore.constants.CommandTypes;
import gamestore.constants.CommandValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

@Controller
public class GameStoreController implements CommandLineRunner {

    private CommandFactory commandFactory;

    @Autowired
    public GameStoreController(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    @Override
    public void run(String... args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String[] commandTokens = reader.readLine().split(CommandValues.SEPARATOR);
            String commandType = commandTokens[0];

            if (CommandTypes.END.equals(commandType)) {
                reader.close();
                return;
            }

            String[] commandArguments = Arrays.copyOfRange(commandTokens, 1, commandTokens.length);

            Executable command = this.commandFactory.createCommand(commandType);

            try {
                if (command != null) {
                    System.out.println(command.execute(commandArguments));
                } else {
                    System.out.println(CommandMessages.INVALID_COMMAND);
                }
            } catch (IndexOutOfBoundsException ex ) {
                System.out.println(CommandMessages.INVALID_ARGUMENTS_COUNT);
            }
        }
    }
}
