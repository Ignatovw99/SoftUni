package controllers;

import enumerations.ReportLevel;
import interfaces.Logger;

public class LoggerMessageParser {
    public static void parse(Logger logger, String messagesAsString) {
        String[] messages = messagesAsString.split(System.lineSeparator());
        for (String message : messages) {
            String[] tokens = message.split("\\|");

            String reportLevel = tokens[0];
            String time = tokens[1];
            String messageText = tokens[2];

            logger.log(time, messageText, ReportLevel.valueOf(reportLevel));
        }
    }
}
