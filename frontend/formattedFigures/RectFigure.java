package frontend.formattedFigures;

import backend.model.Figure;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class RectFigure extends FormattedFigure{

    public RectFigure(Figure figure, Color color, Color borderColor, double borderThickness) {
        super(figure, color, borderColor, borderThickness);
    }

    @Override
    public GraphicsContext redrawCanvas(GraphicsContext gc) {
        gc.setFill(getColor());
        gc.setStroke(getBorderColor());
        gc.setLineWidth(getBorderThickness());
        gc.fillRect(getFigure().getTopLeft().getX() , getFigure().getTopLeft().getY(), getFigure().getXDiameter(), getFigure().getYDiameter());
        gc.strokeRect(getFigure().getTopLeft().getX() , getFigure().getTopLeft().getY(), getFigure().getXDiameter(), getFigure().getYDiameter());
        return gc;
    }
}
