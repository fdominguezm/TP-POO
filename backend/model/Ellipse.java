package backend.model;

public class Ellipse extends Figure {


    public Ellipse(Point topLeft, Point bottomRight) {
        super(topLeft, bottomRight);
    }

    @Override
    public String toString() {
        return String.format("Elipse [ %s , %s ]", getTopLeft(), getBottomRight());
    }

}
