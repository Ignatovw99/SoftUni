package models;

import enumerations.ReportLevel;
import interfaces.Layout;

public class XmlLayout implements Layout {
    @Override
    public String format(ReportLevel reportLevel, String time, String message) {
        return String.format("<log>%n" +
                        "   <date>%s</date>%n" +
                        "   <level>%s</level>%n" +
                        "   <message>%s</message>%n" +
                        "</log>",
                time, reportLevel.toString(), message);
    }
}
