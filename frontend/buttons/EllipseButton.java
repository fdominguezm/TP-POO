package frontend.buttons;

import backend.model.Point;
import frontend.formattedFigures.FormattedEllipse;
import frontend.formattedFigures.FormattedFigure;

public class EllipseButton extends FigureButtons{

    public EllipseButton(){
        super("Elipse");
    }

    @Override
    public FormattedFigure createFigure(Point topLeft, Point bottomRight) {
        return new FormattedEllipse(topLeft, bottomRight);
    }
}
