package backend.model;

public class Square extends Rectangle {

    public Square(Point topLeft, Point bottomRight) {
        super(topLeft, new Point(topLeft.getX() + bottomRight.getY() - topLeft.getY(), bottomRight.getY()));
        getBottomRight().setX(getTopLeft().getX() + getXDiameter());
    }

    @Override
    public String toString() {
        return String.format("Cuadrado [ %s , %s ]", this.getTopLeft(), this.getBottomRight());
    }
}
