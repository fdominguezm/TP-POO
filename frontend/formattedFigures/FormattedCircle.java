package frontend.formattedFigures;

import backend.model.Circle;
import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;


public class FormattedCircle extends OvalFigure{

    public FormattedCircle(Point centerPoint, Point bottomRight, double radius){
        this.figure = new Circle(centerPoint, bottomRight, radius);
    }

}
