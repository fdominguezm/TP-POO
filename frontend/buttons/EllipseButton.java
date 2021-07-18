package frontend.buttons;

import backend.model.Point;
import frontend.formattedFigures.FormattedEllipse;
import frontend.formattedFigures.FormattedFigure;
import javafx.scene.paint.Color;

public class EllipseButton extends FigureButtons{

    public EllipseButton(){
        super("Elipse");
    }

    @Override
    public FormattedFigure createFigure(Point startPoint, Point endPoint, Color fillColor, Color lineColor, double defaultThickness) {
        return new FormattedEllipse(startPoint, endPoint, fillColor, lineColor, defaultThickness);
    }
}
