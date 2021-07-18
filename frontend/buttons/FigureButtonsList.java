package frontend.buttons;

import backend.model.Point;
import frontend.formattedFigures.FormattedFigure;
import javafx.scene.paint.Color;

import java.util.ArrayList;


public class FigureButtonsList extends ArrayList<FigureButtons>{

    public FigureButtonsList(){
        super();
        this.add(new RectangleButton());
        this.add(new CircleButton());
        this.add(new SquareButton());
        this.add(new EllipseButton());
        this.add(new LineButton());
    }

    public boolean isSelected(){
        for (FigureButtons b: this) {
            if(b.isSelected()){
                return true;
            }
        }
        return false;
    }

    public FormattedFigure createFigure(Point startPoint, Point endPoint, Color fillColor, Color lineColor, double defaultThickness){
        for (FigureButtons b: this) {
            if(b.isSelected()){
                return b.createFigure(startPoint, endPoint, fillColor, lineColor, defaultThickness);
            }
        }
        return null;
    }

}
