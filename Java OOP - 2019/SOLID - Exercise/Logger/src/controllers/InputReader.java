package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputReader {
    private BufferedReader bufferedReader;

    public InputReader(InputStreamReader inputStreamReader) {
        this.bufferedReader = new BufferedReader(inputStreamReader);
    }

    public String readAppenderLines() throws IOException {
        int appenderNumber = Integer.parseInt(this.bufferedReader.readLine());
        StringBuilder appenderLines = new StringBuilder();
        for (int i = 0; i < appenderNumber; i++) {
            appenderLines.append(this.bufferedReader.readLine()).append(System.lineSeparator());
        }
        return appenderLines.toString();
    }

    public String readAllMessages(String endString) throws IOException {
        StringBuilder messages = new StringBuilder();
        String line = this.bufferedReader.readLine();
        while (!endString.equals(line)) {
            messages.append(line).append(System.lineSeparator());
            line = this.bufferedReader.readLine();
        }
        return messages.toString();
    }
}
