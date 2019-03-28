package controllers;

import factories.LoggerFactory;
import interfaces.Logger;

import java.io.IOException;
import java.io.InputStreamReader;

public class Engine {
    private static LoggerFactory LOGGER_FACTORY = new LoggerFactory();

    public static void run() throws IOException {
        InputReader inputReader = new InputReader(new InputStreamReader(System.in));
        Logger logger = Engine.LOGGER_FACTORY.produce(inputReader.readAppenderLines());
        LoggerMessageParser.parse(logger, inputReader.readAllMessages("END"));
        System.out.println(logger.toString());
    }
}
