package frontend.formattedFigures;

import backend.model.Ellipse;
import backend.model.Figure;
import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class FormattedEllipse extends OvalFigure {

    public FormattedEllipse(Point startPoint, Point endPoint, Color fillColor, Color lineColor, double defaultThickness) {
        super(new Ellipse(startPoint, endPoint), fillColor, lineColor, defaultThickness);
    }
    @Override
    public FormattedFigure newFigure(Figure figure, Color color, Color borderColor, double borderThickness) {
        return new FormattedEllipse(figure.getTopLeft(), figure.getBottomRight(), color, borderColor, borderThickness);
    }

}
