package p02_OpenClosedPrinciple.p02_DrawingShape;

import p02_OpenClosedPrinciple.p02_DrawingShape.interfaces.Shape;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(new Circle());
        shapes.add(new Rectangle());
        for (Shape shape : shapes) {
            shape.draw();
        }
    }
}
