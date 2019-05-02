package models;

import enumerations.ReportLevel;
import interfaces.Appender;
import interfaces.Logger;

public class MessageLogger implements Logger {
    private Appender[] appenders;

    public MessageLogger(Appender... appenders) {
        this.appenders = appenders;
    }

    @Override
    public void log(String time, String message, ReportLevel reportLevel) {
        this.logMessage(time, message, reportLevel);
    }

    private void logMessage(String time, String message, ReportLevel reportLevel) {
        for (Appender appender : this.appenders) {
            appender.append(reportLevel, time, message);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Logger info");
        for (Appender appender : this.appenders) {
            sb.append(System.lineSeparator()).append(appender.toString());
        }
        return sb.toString();
    }
}
