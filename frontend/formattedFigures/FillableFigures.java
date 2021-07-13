package frontend.formattedFigures;

import javafx.scene.paint.Color;

public abstract class FillableFigures extends FormattedFigure{
    protected Color color;

    public Color getColor() {
        return color;
    }

    public void setColor(Color c) {
        color = c;
    }
}
