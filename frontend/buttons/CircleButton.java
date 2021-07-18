package frontend.buttons;

import backend.model.Point;
import frontend.formattedFigures.FormattedCircle;
import frontend.formattedFigures.FormattedFigure;
import javafx.scene.paint.Color;

public class CircleButton extends FigureButtons{

    public CircleButton(){
        super("Circulo");
    }

    @Override
    public FormattedFigure createFigure(Point startPoint, Point endPoint, Color fillColor, Color lineColor, double defaultThickness) {
        double circleRadius = Math.sqrt(Math.pow(endPoint.getX() - startPoint.getX(),2) + Math.pow(endPoint.getY() - startPoint.getY(),2));
        return new FormattedCircle(startPoint, circleRadius, fillColor, lineColor, defaultThickness);
    }
}
