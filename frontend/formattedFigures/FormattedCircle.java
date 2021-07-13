package frontend.formattedFigures;

import backend.model.Circle;
import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;


public class FormattedCircle extends FormattedFigure{

    public FormattedCircle(Point centerPoint, Point bottomRight, double radius){
        this.figure = new Circle(centerPoint, bottomRight, radius);
    }

    @Override
    public GraphicsContext redrawCanvas(GraphicsContext gc) {
        gc.fillOval(figure.getTopLeft().getX() , figure.getTopLeft().getY(), figure.getXDiameter(), figure.getYDiameter());
        gc.strokeOval(figure.getTopLeft().getX() , figure.getTopLeft().getY(), figure.getXDiameter(), figure.getYDiameter());
        return gc;
    }
}
