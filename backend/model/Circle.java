package backend.model;

public class Circle extends Ellipse {

    public Circle(Point centerPoint, double radius) {
        super(new Point(centerPoint.getX() - radius, centerPoint.getY() - radius), new Point(centerPoint.getX() + radius, centerPoint.getY() + radius));
        this.centerPoint = centerPoint;
    }

    @Override
    public String toString() {
        return String.format("Círculo [Centro: %s, Radio: %.2f]", centerPoint, circleRatio());
    }

    private double circleRatio() {
        return getYDiameter()/2;
    }
}
