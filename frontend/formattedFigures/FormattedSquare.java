package frontend.formattedFigures;

import backend.model.Point;
import backend.model.Square;

public class FormattedSquare extends RectFigure{
    public FormattedSquare(Point topLeft, Point bottomRight){
        this.figure = new Square(topLeft, bottomRight);
    }
}
