package interfaces;

import enumerations.ReportLevel;

public interface Logger {
    void log(String time, String message, ReportLevel reportLevel);
}
