package frontend.buttons;

import backend.model.Figure;
import backend.model.Point;
import backend.model.Rectangle;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ToggleButton;

import java.util.Map;

public class RectangleButton extends FigureButtons{
    private Rectangle rectangle = null;

    public RectangleButton(String name) {
        super(name);
    }

    @Override
    public Figure createFigure(Point topLeft, Point bottomRight) {
        rectangle = new Rectangle(topLeft, bottomRight);
        return rectangle;
    }

    @Override
    public GraphicsContext redrawCanvas(GraphicsContext gc) {
        gc.fillRect(rectangle.getTopLeft().getX(), rectangle.getTopLeft().getY(),
                Math.abs(rectangle.getTopLeft().getX() - rectangle.getBottomRight().getX()), Math.abs(rectangle.getTopLeft().getY() - rectangle.getBottomRight().getY()));
        gc.strokeRect(rectangle.getTopLeft().getX(), rectangle.getTopLeft().getY(),
                Math.abs(rectangle.getTopLeft().getX() - rectangle.getBottomRight().getX()), Math.abs(rectangle.getTopLeft().getY() - rectangle.getBottomRight().getY()));
        return gc;
    }

}
