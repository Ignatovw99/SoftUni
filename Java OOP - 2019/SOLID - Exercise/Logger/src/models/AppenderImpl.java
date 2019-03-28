package models;

import enumerations.ReportLevel;
import interfaces.Appender;
import interfaces.Layout;

public abstract class AppenderImpl implements Appender {
    private static final ReportLevel DEFAULT_REPORT_LEVEL = ReportLevel.INFO;

    private Layout layout;
    private ReportLevel reportLevel;
    private int appendedMessagesCount;

    protected AppenderImpl(Layout layout) {
        this.layout = layout;
        this.reportLevel = AppenderImpl.DEFAULT_REPORT_LEVEL;
    }

    protected AppenderImpl(Layout layout, ReportLevel reportLevel) {
        this(layout);
        this.reportLevel = reportLevel;
    }

    protected Layout getLayout() {
        return this.layout;
    }

    @Override
    public void setReportLevel(ReportLevel reportLevel) {
        this.reportLevel = reportLevel;
    }

    @Override
    public abstract void append(ReportLevel reportLevel, String time, String message);

    @Override
    public boolean canAppend(ReportLevel reportLevel) {
        return this.reportLevel.ordinal() <= reportLevel.ordinal();
    }

    protected void incrementAppendedMessagesCount() {
        this.appendedMessagesCount += 1;
    }

    @Override
    public String toString() {
        return String.format("Appender type: %s, Layout type: %s, Report level: %s, Messages appended: %d",
                this.getClass().getSimpleName(),
                this.layout.getClass().getSimpleName(),
                this.reportLevel.toString(),
                this.appendedMessagesCount);
    }
}
