package backend.model;

public abstract class Figure {

    private final Point topLeft, bottomRight;
    private double xDiameter, yDiameter;

    public Figure(Point topLeft, Point bottomRight) {
        this.bottomRight = bottomRight;
        this.topLeft = topLeft;
        xDiameter = bottomRight.getX() - topLeft.getX();
        yDiameter = bottomRight.getY() - topLeft.getY();
    }

    public boolean belongs(Point p) {
        return Double.compare(p.getX(), getTopLeft().getX()) >= 0
                && Double.compare(p.getX(), getBottomRight().getX()) <= 0
                && Double.compare(p.getY(), getTopLeft().getY()) >= 0
                && Double.compare(p.getY(), getBottomRight().getY()) <= 0;
    }

    public boolean belongs(Point p1, Point p2) {
        return Double.compare(p1.getX(), topLeft.getX()) < 0
                && Double.compare(p1.getY(), topLeft.getY()) < 0
                && Double.compare(p2.getX(), bottomRight.getX()) > 0
                && Double.compare(p2.getY(), bottomRight.getY()) > 0;
    }

    public void move(double x, double y) {
        topLeft.move(x, y);
        bottomRight.move(x, y);
    }


    public double getXDiameter() {
        return xDiameter;
    }

    public double getYDiameter() {
        return yDiameter;
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }
}
