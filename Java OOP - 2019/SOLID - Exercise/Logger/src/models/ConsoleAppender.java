package models;

import enumerations.ReportLevel;
import interfaces.Layout;

public class ConsoleAppender extends AppenderImpl {
    public ConsoleAppender(Layout layout) {
        super(layout);
    }

    public ConsoleAppender(Layout layout, ReportLevel reportLevel) {
        super(layout, reportLevel);
    }

    @Override
    public void append(ReportLevel reportLevel, String time, String message) {
        if (this.canAppend(reportLevel)) {
            String formattedMessage = this.getLayout().format(reportLevel, time, message);
            System.out.println(formattedMessage);
            this.incrementAppendedMessagesCount();
        }
    }
}
