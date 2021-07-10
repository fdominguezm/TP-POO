package backend.model;

public class Rectangle extends Figure {

    public Rectangle(Point topLeft, Point bottomRight) {
        super(topLeft, bottomRight);
    }

    @Override
    public String toString() {
        return String.format("Rectángulo [ %s , %s ]", getTopLeft(), getBottomRight());
    }
}
