package p02_OpenClosedPrinciple.p01_FileStream;

public class Progress {
    private final Document document;

    public Progress(Document document)
    {
        this.document = document;
    }

    public int getCurrentPercent()
    {
        return this.document.getSent() * 100 / this.document.getLength();
    }
}
