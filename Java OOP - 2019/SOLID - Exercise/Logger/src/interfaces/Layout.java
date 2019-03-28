package interfaces;

import enumerations.ReportLevel;

public interface Layout {
    String format(ReportLevel reportLevel, String time, String message);
}
