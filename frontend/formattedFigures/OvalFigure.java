package frontend.formattedFigures;

import javafx.scene.canvas.GraphicsContext;

public abstract class OvalFigure extends FillableFigures{
    @Override
    public GraphicsContext redrawCanvas(GraphicsContext gc) {
        gc.fillOval(figure.getTopLeft().getX() , figure.getTopLeft().getY(), figure.getXDiameter(), figure.getYDiameter());
        gc.strokeOval(figure.getTopLeft().getX() , figure.getTopLeft().getY(), figure.getXDiameter(), figure.getYDiameter());
        return gc;
    }
}
