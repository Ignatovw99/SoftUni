package hell.core;

import hell.interfaces.InputReader;
import hell.interfaces.Manager;
import hell.interfaces.OutputWriter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Engine {
    private InputReader inputReader;
    private OutputWriter outputWriter;
    private Manager applicationManager;

    public Engine(InputReader inputReader, OutputWriter outputWriter, Manager applicationManager) {
        this.inputReader = inputReader;
        this.outputWriter = outputWriter;
        this.applicationManager = applicationManager;
    }

    public void run() {
        String inputLine = this.inputReader.readLine();
        while (true) {
            String[] tokens = inputLine.split("\\s+");
            String command = tokens[0];
            List<String> arguments = Arrays.stream(tokens).skip(1).collect(Collectors.toList());
            String commandMessage = null;
            switch (command) {
                case "Hero":
                    commandMessage = this.applicationManager.addHero(arguments);
                    break;
                case "Item":
                    commandMessage = this.applicationManager.addItem(arguments);
                    break;
                case "Recipe":
                    commandMessage = this.applicationManager.addRecipe(arguments);
                    break;
                case "Inspect":
                    commandMessage = this.applicationManager.inspect(arguments);
                    break;
                case "Quit":
                    this.outputWriter.writeLine(this.applicationManager.quit());
                    return;
            }
            this.outputWriter.writeLine(commandMessage);
            inputLine = this.inputReader.readLine();
        }
    }
}
