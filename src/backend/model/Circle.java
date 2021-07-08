package backend.model;

public class Circle extends Figure {

    protected final Point centerPoint;
    protected final double radius;

    public Circle(Point centerPoint, double radius) {
        this.centerPoint = centerPoint;
        this.radius = radius;
    }

    @Override
    public String toString() {
        return String.format("Círculo [Centro: %s, Radio: %.2f]", centerPoint, radius);
    }

    public Point getCenterPoint() {
        return centerPoint;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    protected Point[] getPoints() {
        return new Point[]{centerPoint};
    }

    @Override
    public boolean belongs(Point p) {
        return Double.compare(Math.sqrt(Math.pow(centerPoint.getX() - p.getX(), 2) +
                Math.pow(centerPoint.getY() - p.getY(), 2)), radius) <= 0;
    }
}
