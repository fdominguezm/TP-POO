package frontend.buttons;

import backend.model.Circle;
import backend.model.Figure;
import backend.model.Point;
import backend.model.Rectangle;
import frontend.formattedFigures.FormattedCircle;
import frontend.formattedFigures.FormattedFigure;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ToggleButton;

import java.util.Map;

public abstract class FigureButtons extends ToggleButton{

    public FigureButtons(String name) {
        super(name);
    }

    public abstract FormattedFigure createFigure(Point topLeft, Point bottomRight);

}
