package frontend.formattedFigures;

import backend.model.Ellipse;
import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;

public class FormattedEllipse extends OvalFigure{

    public FormattedEllipse(Point topLeft, Point bottomRight){
        this.figure = new Ellipse(topLeft,bottomRight);
    }

}
