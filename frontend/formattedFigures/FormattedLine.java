package frontend.formattedFigures;

import backend.model.Figure;
import backend.model.Line;
import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class FormattedLine extends FormattedFigure{

    public FormattedLine(Point startPoint, Point endPoint, Color fillColor, Color lineColor, double defaultThickness) {
        super(new Line(startPoint, endPoint), fillColor, lineColor, defaultThickness);
    }

    @Override
    public GraphicsContext redrawCanvas(GraphicsContext gc) {
        if (isSelected()) {
            gc.setStroke(Color.RED);
        }else{
            gc.setStroke(getBorderColor());
        }
        gc.setLineWidth(getBorderThickness());
        gc.strokeLine(getFigure().getTopLeft().getX(), getFigure().getTopLeft().getY(), getFigure().getBottomRight().getX(), getFigure().getBottomRight().getY());
        return gc;
    }

    @Override
    public FormattedFigure newFigure(Figure figure, Color color, Color borderColor, double borderThickness) {
        return new FormattedLine(figure.getTopLeft(), figure.getBottomRight(), color, borderColor, borderThickness);
    }

}
