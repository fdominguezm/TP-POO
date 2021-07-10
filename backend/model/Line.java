package backend.model;

public class Line extends Figure{

    public Line(Point topLeft, Point bottomRight) {
        super(topLeft, bottomRight);
    }

    @Override
    public boolean belongs (Point p) {
        return false;
    }

    @Override
    public boolean belongs (Point p1, Point p2) {
        return super.belongs(p1, p2) || super.belongs(p2, p1);
    }

    @Override
    public String toString () {
        return String.format("Line [ %s, %s]", getTopLeft(), getBottomRight());
    }
}
