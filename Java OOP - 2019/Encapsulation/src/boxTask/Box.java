package boxTask;

public class Box {
    private double length;
    private double width;
    private double height;

    public Box(double length, double width, double height) {
        this.setLength(length);
        this.setWidth(width);
        this.setHeight(height);
    }

    private void setLength(double length) {
        if (!this.isSideSizeValid(length)) {
            throw new IllegalArgumentException("Length cannot be zero or negative.");
        }
        this.length = length;
    }

    private void setWidth(double width) {
        if (!this.isSideSizeValid(width)) {
            throw new IllegalArgumentException("Width cannot be zero or negative.");
        }
        this.width = width;
    }

    private void setHeight(double height) {
        if (!this.isSideSizeValid(height)) {
            throw new IllegalArgumentException("Height cannot be zero or negative.");
        }
        this.height = height;
    }

    private boolean isSideSizeValid(double side) {
        return side >= 1;
    }

    public double calculateSurfaceArea() {
        return 2 * this.width * this.length + 2 * this.length * this.height + 2 * this.width * this.height;
    }

    public double calculateLateralSurfaceArea() {
        return 2 * this.length * this.height + 2 * this.width * this.height;
    }

    public double calculateVolume() {
        return this.length * this.width * this.height;
    }

    @Override
    public String toString() {
        return String.format("Surface area - %.2f%n" +
                "Lateral Surface Area - %.2f%n" +
                "Volume - %.2f", this.calculateSurfaceArea(), this.calculateLateralSurfaceArea(), this.calculateVolume());
    }
}
