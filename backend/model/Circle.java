package backend.model;

public class Circle extends Ellipse {

    public Circle(Point centerPoint, Point bottomRight, double radius) {
        super(new Point(centerPoint.getX() - radius, centerPoint.getY() + radius), bottomRight);
        this.centerPoint = centerPoint;
    }

    @Override
    public String toString() {
        return String.format("Círculo [Centro: %s, Radio: %.2f]", centerPoint, circleRatio());
    }

    private double circleRatio() {
        return getXDiameter()/2;
    }
}
