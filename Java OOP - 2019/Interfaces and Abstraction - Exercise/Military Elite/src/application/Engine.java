package application;

import inputoutput.InputReader;
import inputoutput.OutputWriter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Engine {
    public static void run() throws IOException {
        InputReader reader = new InputReader(new InputStreamReader(System.in), "End");
        CommandParser commandParser = new CommandParser(reader.readAllLines());
        Army army = commandParser.parseCommandAndGetArmy();
        OutputWriter writer = new OutputWriter(new OutputStreamWriter(System.out));
        writer.writeWholeInformation(army);
    }
}
