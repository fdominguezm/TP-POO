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

    public void move(double x, double y) {
        topLeft.setX(topLeft.getX() + x);
        topLeft.setY(topLeft.getY() + y);
    }

    public boolean belongs(Point p) {
        return Double.compare(p.getX(), getTopLeft().getX()) >= 0 && Double.compare(p.getX(), getBottomRight().getX()) <= 0 &&
                Double.compare(p.getY(), getTopLeft().getY()) >= 0 && Double.compare(p.getY(), getBottomRight().getY()) <= 0;
    }

    public void setXDiameter(double xDiameter) {
        this.xDiameter = xDiameter;
    }

    public void setYDiameter(double yDiameter) {
        this.yDiameter = yDiameter;
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
