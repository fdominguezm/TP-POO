package frontend.formattedFigures;

import backend.model.Circle;
import backend.model.Figure;
import backend.model.Point;
import backend.model.Rectangle;
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

    public FormattedFigure setBorderThickness(double borderThickness) {
        return newFigure(this.figure, this.color, this.borderColor, borderThickness);
    }

    public FormattedFigure setColor(Color color) {
        return newFigure(this.figure, color, this.borderColor, this.borderThickness);
    }
    public FormattedFigure move(double x, double y) {
        Point tpl = new Point(getFigure().getTopLeft().getX() + x,getFigure().getTopLeft().getY() + y);
        Point btr = new Point(getFigure().getBottomRight().getX() + x,getFigure().getBottomRight().getY() + y);
        Figure aux = new Rectangle(tpl,btr);
        return newFigure(aux, getColor(), getBorderColor(), getBorderThickness());
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