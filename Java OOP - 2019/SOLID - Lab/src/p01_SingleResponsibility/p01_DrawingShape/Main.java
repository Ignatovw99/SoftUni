package p01_SingleResponsibility.p01_DrawingShape;

import p01_SingleResponsibility.p01_DrawingShape.interfaces.Renderer;

public class Main {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(5, 7);
        Renderer renderer = new RendererImpl();
        rectangle.draw(renderer);
    }
}
