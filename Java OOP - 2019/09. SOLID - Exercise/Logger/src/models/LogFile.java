package models;

import interfaces.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class LogFile implements File {
    private static final String DEFAULT_FILE_PATH = "output.txt";

    private StringBuilder text;
    private String filePath;

    public LogFile() {
        this.text = new StringBuilder();
        this.filePath = LogFile.DEFAULT_FILE_PATH;
    }

    public LogFile(String filePath) {
        this();
        this.filePath = filePath;
    }

    @Override
    public void write(String message) {
        try {
            this.text.append(message).append(System.lineSeparator());
            Files.write(Path.of(this.filePath), message.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public int getSize() {
        return this.calculateSize();
    }

    private int calculateSize() {
        int size = 0;
        char[] textAsCharArray = this.text.toString().toCharArray();
        for (char character : textAsCharArray) {
            if (Character.isAlphabetic(character)) {
                size += character;
            }
        }
        return size;
    }
}
