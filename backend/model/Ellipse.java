package backend.model;

public class Ellipse extends Figure {

    protected Point centerPoint;
    public Ellipse(Point topLeft, Point bottomRight) {
        super(topLeft, bottomRight);
        centerPoint = new Point(topLeft.getX() + getXDiameter()/2, topLeft.getY() + getYDiameter()/2);
    }

    @Override
    public void move(double x, double y) {
        super.move(x, y);
        centerPoint.setX(getTopLeft().getX() + getXDiameter()/2);
        centerPoint.setY(getTopLeft().getY() + getYDiameter()/2);
    }

    @Override
    public boolean belongs(Point p) {
        return (Math.pow((p.getX() - centerPoint.getX()), 2) / Math.pow(this.getXDiameter(), 2))
                + (Math.pow((p.getY() - centerPoint.getX()), 2) / Math.pow(this.getYDiameter(), 2)) <= 1;
    }

    @Override
    public String toString() {
        return String.format("Elipse [ Center: %s , XDiam: %.2f YDiam: %.2f ]", centerPoint, getXDiameter(), getYDiameter());
    }

}
