package rectangle;

public class Rectangle {
    private Point topRightCorner;
    private Point bottomLeftCorner;

    public Rectangle(Point bottomLeftCorner, Point topRightCorner) {
        this.bottomLeftCorner = new Point(bottomLeftCorner.getX(), bottomLeftCorner.getY());
        this.topRightCorner = new Point(topRightCorner.getX(), topRightCorner.getY());
    }

    public boolean contains(Point point) {
        boolean isOnHorizontal = point.getX() >= this.bottomLeftCorner.getX() &&
                point.getX() <= this.topRightCorner.getX();
        boolean isOnVertical = point.getY() >= this.bottomLeftCorner.getY() &&
                point.getY() <= this.topRightCorner.getY();

        return isOnHorizontal && isOnVertical;
    }
}
