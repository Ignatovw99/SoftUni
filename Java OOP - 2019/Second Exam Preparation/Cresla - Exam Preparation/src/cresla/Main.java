package cresla;

import cresla.core.ApplicationManager;
import cresla.core.Engine;
import cresla.interfaces.InputReader;
import cresla.interfaces.Manager;
import cresla.interfaces.OutputWriter;
import cresla.io.ConsoleInputReader;
import cresla.io.ConsoleOutputWriter;

public class Main {
    public static void main(String[] args) {
        InputReader inputReader = new ConsoleInputReader();
        OutputWriter outputWriter = new ConsoleOutputWriter();
        Manager manager = new ApplicationManager();
        Engine engine = new Engine(inputReader, outputWriter, manager);
        engine.run();
    }
}
