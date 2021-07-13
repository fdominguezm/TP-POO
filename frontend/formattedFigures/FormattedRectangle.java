package frontend.formattedFigures;

import backend.model.Point;
import backend.model.Rectangle;
import javafx.scene.canvas.GraphicsContext;

public class FormattedRectangle extends RectFigure{

    public FormattedRectangle(Point topLeft, Point bottomRight){
        this.figure = new Rectangle(topLeft, bottomRight);
    }
}
