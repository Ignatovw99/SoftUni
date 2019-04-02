package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Executor {
    private static final String END_STRING = "End";
    private static final CommandHandler COMMAND_HANDLER = new CommandHandler();

    public static void execute(InputStreamReader inputStreamReader) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String inputLine = bufferedReader.readLine();
        while (!END_STRING.equals(inputLine)) {
            try {
                String commandResult = Executor.COMMAND_HANDLER.handle(inputLine);
                if (commandResult != null && !commandResult.equals("")) {
                    System.out.println(commandResult);
                }
            } catch (Throwable exception) {
                System.out.println(exception.getMessage());
            }
            inputLine = bufferedReader.readLine();
        }
    }
}
