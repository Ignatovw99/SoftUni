package cresla.core;

import cresla.interfaces.InputReader;
import cresla.interfaces.Manager;
import cresla.interfaces.OutputWriter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Engine {
    private InputReader inputReader;
    private OutputWriter outputWriter;
    private Manager manager;

    public Engine(InputReader inputReader, OutputWriter outputWriter, Manager manager) {
        this.inputReader = inputReader;
        this.outputWriter = outputWriter;
        this.manager = manager;
    }

    public void run() {
        String inputLine = this.inputReader.readLine();
        while (true) {
            String[] tokens = inputLine.split("\\s+");
            List<String> arguments = Arrays.stream(tokens)
                    .skip(1)
                    .collect(Collectors.toList());
            String command = tokens[0];

            String output = "";

            switch (command) {
                case "Reactor":
                    output = this.manager.reactorCommand(arguments);
                    break;
                case "Module":
                    output = this.manager.moduleCommand(arguments);
                    break;
                case "Report":
                    output = this.manager.reportCommand(arguments);
                    break;
                case "Exit":
                    output = this.manager.exitCommand(arguments);
                    break;
            }

            this.outputWriter.writeLine(output);

            if ("Exit".equals(inputLine)) {
                return;
            }
            inputLine = this.inputReader.readLine();
        }
    }
}
