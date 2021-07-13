package frontend.buttons;

import backend.model.Point;
import frontend.formattedFigures.FormattedCircle;
import frontend.formattedFigures.FormattedFigure;
import javafx.scene.canvas.GraphicsContext;

public class CircleButton extends FigureButtons{

    public CircleButton(){
        super("Circulo");
    }


    @Override
    public FormattedFigure createFigure(Point startPoint, Point endPoint) {
        double circleRadius = Math.abs(endPoint.getX() - startPoint.getX());
        return new FormattedCircle(startPoint, endPoint, circleRadius);
    }
}
