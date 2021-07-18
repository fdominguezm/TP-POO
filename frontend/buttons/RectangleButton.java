package frontend.buttons;

import backend.model.Point;
import frontend.formattedFigures.FormattedFigure;
import frontend.formattedFigures.FormattedRectangle;
import javafx.scene.paint.Color;

public class RectangleButton extends FigureButtons {

    public RectangleButton() {
        super("Rectangulo");
    }

    @Override
    public FormattedFigure createFigure(Point startPoint, Point endPoint, Color fillColor, Color lineColor, double defaultThickness) {
        return new FormattedRectangle(startPoint, endPoint, fillColor, lineColor, defaultThickness);
    }

}
