package frontend.buttons;

import backend.model.Point;
import frontend.formattedFigures.FormattedFigure;
import frontend.formattedFigures.FormattedLine;
import javafx.scene.paint.Color;

public class LineButton extends FigureButtons{

    public LineButton(){
        super("Linea");
    }

    @Override
    public FormattedFigure createFigure(Point startPoint, Point endPoint, Color fillColor, Color lineColor, double defaultThickness) {
        return new FormattedLine(startPoint, endPoint, fillColor, lineColor, defaultThickness);
    }
}
