package frontend.buttons;

import backend.model.Circle;
import backend.model.Figure;
import backend.model.Point;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ToggleButton;

public class CircleButton extends FigureButtons{
    private Circle circle = null;

    public CircleButton(String name){
        super(name);
    }


    @Override
    public Figure createFigure(Point topLeft, Point bottomRight) {
        circle = new Circle(topLeft, bottomRight);
        return circle;
    }

    @Override
    public GraphicsContext redrawCanvas(GraphicsContext gc) {
        gc.fillOval(circle.getCenterPoint().getX() - circle.getRadius(), circle.getCenterPoint().getY() - circle.getRadius(), diameter, diameter);
        gc.strokeOval(circle.getCenterPoint().getX() - circle.getRadius(), circle.getCenterPoint().getY() - circle.getRadius(), diameter, diameter);
        return gc;
    }
}
