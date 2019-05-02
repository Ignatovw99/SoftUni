package models;

import enumerations.ReportLevel;
import interfaces.File;
import interfaces.Layout;

public class FileAppender extends AppenderImpl {
    private File file;

    public FileAppender(Layout layout) {
        super(layout);
        this.file = new LogFile();
    }

    public FileAppender(Layout layout, ReportLevel reportLevel) {
        super(layout, reportLevel);
        this.file = new LogFile();
    }

    public FileAppender(Layout layout, File file) {
        this(layout);
        this.file = file;
    }

    public FileAppender(Layout layout, File file, ReportLevel reportLevel) {
        super(layout, reportLevel);
        this.file = file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public void append(ReportLevel reportLevel, String time, String message) {
        if (this.canAppend(reportLevel)) {
            String formattedMessage = this.getLayout().format(reportLevel, time, message);
            this.file.write(formattedMessage);
            this.incrementAppendedMessagesCount();
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", File size: " + this.file.getSize();
    }
}
