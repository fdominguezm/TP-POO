package frontend.formattedFigures;

import backend.model.Circle;
import backend.model.Figure;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class FormattedFigure {
    protected Figure figure;
    protected Color color;

    public abstract GraphicsContext redrawCanvas(GraphicsContext gc);

    public void setColor(Color c) {
        color = c;
    }

    public Color getColor() {
        return color;
    }

    public Figure getFigure() {
        return figure;
    }
}
