package frontend.buttons;

import backend.model.Point;
import frontend.formattedFigures.FormattedFigure;
import frontend.formattedFigures.FormattedSquare;
import javafx.scene.paint.Color;

public class SquareButton extends FigureButtons{
    public SquareButton() {
        super("Cuadrado");
    }

    @Override
    public FormattedFigure createFigure(Point startPoint, Point endPoint, Color fillColor, Color lineColor, double defaultThickness) {
        return new FormattedSquare(startPoint, endPoint, fillColor, lineColor, defaultThickness);
    }
}
