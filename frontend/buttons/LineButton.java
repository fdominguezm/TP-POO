package frontend.buttons;

import backend.model.Point;
import frontend.formattedFigures.FormattedFigure;
import frontend.formattedFigures.FormattedLine;

public class LineButton extends FigureButtons{

    public LineButton(){
        super("Linea");
    }

    @Override
    public FormattedFigure createFigure(Point topLeft, Point bottomRight) {
        return new FormattedLine(topLeft, bottomRight);
    }
}
