package hell;

import hell.core.ApplicationManager;
import hell.core.Engine;
import hell.factories.HeroFactory;
import hell.interfaces.InputReader;
import hell.interfaces.Manager;
import hell.interfaces.OutputWriter;
import hell.io.ConsoleInputReader;
import hell.io.ConsoleOutputWriter;

public class Main {
    public static void main(String[] args) {
        InputReader reader = new ConsoleInputReader();
        OutputWriter writer = new ConsoleOutputWriter();
        HeroFactory heroFactory = new HeroFactory();
        Manager manager = new ApplicationManager(heroFactory);
        Engine engine = new Engine(reader, writer, manager);
        engine.run();
    }
}