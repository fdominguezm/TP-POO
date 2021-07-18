package frontend.formattedFigures;

import backend.model.Circle;
import backend.model.Figure;
import backend.model.Point;
import javafx.scene.paint.Color;

public class FormattedCircle extends OvalFigure{

    public FormattedCircle(Point centerPoint, double radius, Color fillColor, Color borderColor, double borderThickness){
        super(new Circle(centerPoint, radius), fillColor, borderColor, borderThickness);
    }

    @Override
    public FormattedFigure newFigure(Figure figure, Color color, Color borderColor, double borderThickness) {
        double radius = figure.getXDiameter()/2;
        Point center = new Point(figure.getTopLeft().getX() + radius, figure.getTopLeft().getY() + radius);
        return new FormattedCircle(center, radius, color, borderColor, borderThickness);
    }

}
