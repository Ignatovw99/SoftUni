package interfaces;

import enumerations.ReportLevel;

public interface Appender {
    void append(ReportLevel reportLevel, String time, String message);
    void setReportLevel(ReportLevel reportLevel);
    boolean canAppend(ReportLevel reportLevel);
}
