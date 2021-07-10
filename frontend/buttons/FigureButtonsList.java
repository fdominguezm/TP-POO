package frontend.buttons;

import backend.model.Figure;
import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;

import java.util.ArrayList;

public class FigureButtonsList extends ArrayList<FigureButtons>{

    public FigureButtonsList(){
        super();
        this.add(new RectangleButton("Rectángulo"));
        this.add(new CircleButton("Círculo"));
    }

    public boolean isSelected(){
        for (FigureButtons b: this) {
            if(b.isSelected()){
                return true;
            }
        }
        return false;
    }

    public Figure createFigure(Point topLeft, Point bottomRight){
        for (FigureButtons b: this) {
            if(b.isSelected()){
                return b.createFigure(topLeft,bottomRight);
            }
        }
        return null;
    }

    public GraphicsContext redrawCanvas(GraphicsContext gc){
        for (FigureButtons b: this) {
            if(b.isSelected()){
                return b.redrawCanvas(gc);
            }
        }
        return null;
    }
}
