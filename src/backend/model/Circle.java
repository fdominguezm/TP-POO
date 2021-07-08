package backend.model;

public class Circle extends Figure {

    public Circle(Point topLeft, Point bottomRight) {
        super(topLeft, bottomRight);
        getBottomRight().setX(getTopLeft().getX() + getXDiameter());
        this.setXDiameter(getYDiameter());
    }

    @Override
    public String toString() {
        return String.format("Círculo [Centro: %s, Radio: %.2f]", this.getTopLeft(), circleRatio());
    }

    private double circleRatio() {
        return getXDiameter()/2;
    }
}
