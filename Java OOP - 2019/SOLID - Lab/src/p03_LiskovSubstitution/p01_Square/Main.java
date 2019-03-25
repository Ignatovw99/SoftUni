package p03_LiskovSubstitution.p01_Square;

public class Main {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle();
        Square square = new Square();

        printArea(rectangle);
        printArea(square);
    }

    private static void printArea(Shape shape) {
        System.out.println(shape.getArea());
    }
}
