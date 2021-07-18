package frontend.formattedFigures;

import backend.model.Figure;
import backend.model.Point;
import backend.model.Rectangle;
import javafx.scene.paint.Color;

public class FormattedRectangle extends RectFigure{

    public FormattedRectangle(Point topLeft, Point bottomRight, Color fillColor, Color borderColor, double borderThickness){
        super(new Rectangle(topLeft, bottomRight), fillColor, borderColor, borderThickness);
    }

    @Override
    public FormattedFigure newFigure(Figure figure, Color color, Color borderColor, double borderThickness) {
        return new FormattedRectangle(figure.getTopLeft(), figure.getBottomRight(), color, borderColor, borderThickness);
    }
}
