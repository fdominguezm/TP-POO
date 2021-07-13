package frontend.buttons;

import backend.model.Point;
import backend.model.Rectangle;
import frontend.formattedFigures.FormattedCircle;
import frontend.formattedFigures.FormattedFigure;
import frontend.formattedFigures.FormattedRectangle;
import javafx.scene.canvas.GraphicsContext;

public class RectangleButton extends FigureButtons {

    public RectangleButton() {
        super("Rectangulo");
    }

    @Override
    public FormattedFigure createFigure(Point topLeft, Point bottomRight) {
        return new FormattedRectangle(topLeft, bottomRight);
    }

}
