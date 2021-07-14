package frontend.formattedFigures;

import backend.model.Figure;
import backend.model.Point;
import backend.model.Rectangle;
import backend.model.Square;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class FormattedSquare extends RectFigure {
    public FormattedSquare(Point topLeft, Point bottomRight, Color fillColor, Color borderColor, double borderThickness){
        super(new Square(topLeft, bottomRight), fillColor, borderColor, borderThickness);
    }

    @Override
    public FormattedFigure newFigure(Figure figure, Color color, Color borderColor, double borderThickness) {
        return new FormattedSquare(figure.getTopLeft(), figure.getBottomRight(), color, borderColor, borderThickness);
    }
}
