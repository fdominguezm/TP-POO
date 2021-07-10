package frontend.buttons;

import backend.model.Circle;
import backend.model.Figure;
import backend.model.Point;
import backend.model.Rectangle;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ToggleButton;

import java.util.Map;

public abstract class FigureButtons extends ToggleButton{

    public FigureButtons(String name) {
        super(name);
    }

    public abstract Figure createFigure(Point topLeft, Point bottomRight);

    public abstract GraphicsContext redrawCanvas(GraphicsContext gc);
}
