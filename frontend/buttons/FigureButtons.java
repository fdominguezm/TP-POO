package frontend.buttons;

import backend.model.Point;
import frontend.formattedFigures.FormattedFigure;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;

public abstract class FigureButtons extends ToggleButton{

    public FigureButtons(String name) {
        super(name);
    }

    public abstract FormattedFigure createFigure(Point startPoint, Point endPoint, Color fillColor, Color lineColor, double defaultThickness);

}
