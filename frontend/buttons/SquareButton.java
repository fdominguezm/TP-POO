package frontend.buttons;

import backend.model.Point;
import frontend.formattedFigures.FormattedFigure;
import frontend.formattedFigures.FormattedSquare;

public class SquareButton extends FigureButtons{
    public SquareButton() {
        super("Cuadrado");
    }

    @Override
    public FormattedFigure createFigure(Point topLeft, Point bottomRight) {
        return new FormattedSquare(topLeft, bottomRight);
    }
}
