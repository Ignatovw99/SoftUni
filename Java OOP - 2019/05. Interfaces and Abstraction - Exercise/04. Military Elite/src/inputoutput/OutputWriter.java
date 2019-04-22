package inputoutput;

import application.Army;
import interfaces.Soldier;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class OutputWriter {
    private BufferedWriter writer;

    public OutputWriter(OutputStreamWriter streamWriter) {
        this.writer = new BufferedWriter(streamWriter);
    }

    public void writeWholeInformation(Army army) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        for (Soldier soldier : army.getSoldiers()) {
            stringBuilder.append(soldier.toString()).append(System.lineSeparator());
        }
        this.writer.write(stringBuilder.toString());
        this.writer.close();
    }
}
