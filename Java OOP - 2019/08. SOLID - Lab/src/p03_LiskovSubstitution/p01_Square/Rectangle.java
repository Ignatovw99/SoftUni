package p03_LiskovSubstitution.p01_Square;

public class Rectangle extends FourSidesShape {
    @Override
    public double getArea() {
       return this.getWidth() * this.getHeight();
    }
}
