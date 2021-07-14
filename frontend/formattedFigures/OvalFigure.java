package frontend.formattedFigures;

import backend.model.Figure;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class OvalFigure extends FormattedFigure{

    public OvalFigure(Figure figure, Color color, Color borderColor, double borderThickness) {
        super(figure, color, borderColor, borderThickness);
    }

    @Override
    public GraphicsContext redrawCanvas(GraphicsContext gc) {
        gc.setFill(getColor());
        gc.setStroke(getBorderColor());
        gc.setLineWidth(getBorderThickness());
        gc.fillOval(getFigure().getTopLeft().getX() , getFigure().getTopLeft().getY(), getFigure().getXDiameter(), getFigure().getYDiameter());
        gc.strokeOval(getFigure().getTopLeft().getX() , getFigure().getTopLeft().getY(), getFigure().getXDiameter(), getFigure().getYDiameter());
        return gc;
    }


}
