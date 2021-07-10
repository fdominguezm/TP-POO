package backend.model;

public class Ellipse extends Figure {

    protected Point centerPoint;
    public Ellipse(Point topLeft, Point bottomRight) {
        super(topLeft, bottomRight);
        centerPoint = new Point(topLeft.getX() + getXDiameter()/2, topLeft.getY() + getYDiameter()/2);
    }

    @Override
    public String toString() {
        return String.format("Elipse [ Center: %s , XDiam: %.2f YDiam: %.2f ]", centerPoint, getXDiameter(), getYDiameter());
    }

}
