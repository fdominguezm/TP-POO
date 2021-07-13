package frontend.formattedFigures;

import backend.model.Line;
import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;

public class FormattedLine extends FormattedFigure{

    public FormattedLine(Point topLeft, Point bottomRight){
        this.figure = new Line(topLeft,bottomRight);
    }

    @Override
    public GraphicsContext redrawCanvas(GraphicsContext gc) {
        gc.strokeLine(figure.getTopLeft().getX() , figure.getTopLeft().getY(), figure.getXDiameter(), figure.getYDiameter());
        return gc;
    }

}
