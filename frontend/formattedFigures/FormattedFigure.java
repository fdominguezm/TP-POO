package frontend.formattedFigures;

import backend.model.Circle;
import backend.model.Figure;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class FormattedFigure {
    protected Figure figure;
    protected Color borderColor;
    protected int borderThickness;

    public abstract GraphicsContext redrawCanvas(GraphicsContext gc);

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public void setBorderThickness(int borderThickness) {
        this.borderThickness = borderThickness;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public int getBorderThickness() {
        return borderThickness;
    }

    public Figure getFigure() {
        return figure;
    }
}
