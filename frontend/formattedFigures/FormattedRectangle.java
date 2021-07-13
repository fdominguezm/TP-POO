package frontend.formattedFigures;

import backend.model.Point;
import backend.model.Rectangle;
import javafx.scene.canvas.GraphicsContext;

public class FormattedRectangle extends FormattedFigure{

    public FormattedRectangle(Point topLeft, Point bottomRight){
        this.figure = new Rectangle(topLeft, bottomRight);
    }

    @Override
    public GraphicsContext redrawCanvas(GraphicsContext gc) {
        gc.fillRect(figure.getTopLeft().getX(), figure.getTopLeft().getY(), figure.getXDiameter(), figure.getYDiameter());
        gc.strokeRect(figure.getTopLeft().getX(), figure.getTopLeft().getY(), figure.getXDiameter(), figure.getYDiameter());
        return gc;
    }
}
