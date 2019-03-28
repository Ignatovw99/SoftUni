package models;

import enumerations.ReportLevel;
import interfaces.Layout;

public class SimpleLayout implements Layout {
    @Override
    public String format(ReportLevel reportLevel, String time, String message) {
        return String.format("%s - %s - %s",
                time,
                reportLevel.toString(),
                message);
    }
}
