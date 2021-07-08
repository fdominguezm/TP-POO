package backend.model;

public abstract class Figure {
    protected abstract Point[] getPoints();

    public void move(double x, double y) {
        for(Point p : this.getPoints()) {
            p.setX(p.getX() + x);
            p.setY(p.getY() + y);
        }
    }

    public abstract boolean belongs(Point p);
}
