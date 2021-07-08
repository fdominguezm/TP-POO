package backend.model;

public class Square extends Rectangle {

    public Square(Point topLeft, Point bottomRight) {
        super(topLeft, bottomRight);
        getBottomRight().setX(getTopLeft().getX() + getXDiameter());
        this.setXDiameter(getYDiameter());
    }

    @Override
    public String toString() {
        return String.format("Cuadrado [ %s , %s ]", this.getTopLeft(), this.getBottomRight());
    }
}
