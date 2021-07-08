package backend.model;

public class Rectangle extends Figure {

    private final Point topLeft, bottomRight;

    public Rectangle(Point topLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    @Override
    public String toString() {
        return String.format("Rectángulo [ %s , %s ]", topLeft, bottomRight);
    }

    @Override
    protected Point[] getPoints() {
        return new Point[]{topLeft, bottomRight};
    }

    @Override
    public boolean belongs(Point p) {
        return Double.compare(p.getX(), topLeft.getX()) >= 0 && Double.compare(p.getX(), bottomRight.getX()) <= 0 &&
                Double.compare(p.getY(), topLeft.getY()) >= 0 && Double.compare(p.getY(), bottomRight.getY()) <= 0;
    }

}
