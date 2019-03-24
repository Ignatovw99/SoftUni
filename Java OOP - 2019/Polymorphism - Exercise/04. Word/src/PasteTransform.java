public class PasteTransform implements TextTransform {
    private CutTransform cutTransform;

    protected PasteTransform(CutTransform cutTransform) {
        this.cutTransform = cutTransform;
    }

    @Override
    public void invokeOn(StringBuilder text, int startIndex, int endIndex) {
        StringBuilder lastCut = this.cutTransform.getLastCut();
        text.replace(startIndex, endIndex, lastCut.toString());
    }
}
