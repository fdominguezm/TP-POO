package frontend.formattedFigures;

import backend.model.Figure;
import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class  FormattedFigure {
    private Figure figure;
    private Color color;
    private Color borderColor;
    private double borderThickness;
    private boolean isSelected = false;

    public FormattedFigure(Figure figure, Color color, Color borderColor, double borderThickness) {
        this.figure = figure;
        this.borderColor = borderColor;
        this.borderThickness = borderThickness;
        this.color = color;
    }

    public abstract GraphicsContext redrawCanvas(GraphicsContext gc);

    public FormattedFigure setBorderColor(Color borderColor) {
        return newFigure(this.figure, this.color, borderColor, this.borderThickness);
    }

    public boolean belongs(Point p1, Point p2){
        return figure.belongs(p1,p2);
    }

    public FormattedFigure setBorderThickness(double borderThickness) {
        return newFigure(this.figure, this.color, this.borderColor, borderThickness);
    }

    public FormattedFigure setColor(Color color) {
        return newFigure(this.figure, color, this.borderColor, this.borderThickness);
    }
    public void move(double x, double y) {
        figure.move(x, y);
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public double getBorderThickness() {
        return borderThickness;
    }

    public Figure getFigure() {
        return figure;
    }

    public Color getColor() {
        return color;
    }

    public abstract FormattedFigure newFigure(Figure figure, Color color, Color borderColor, double borderThickness);

    public boolean isSelected(){
        return isSelected;
    }

    public void select(){
        isSelected = true;
    }

    public void unselect(){
        isSelected = false;
    }

    @Override
    public  String toString(){
        return figure.toString();
    }

}