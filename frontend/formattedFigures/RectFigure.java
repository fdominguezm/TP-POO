package frontend.formattedFigures;

import javafx.scene.canvas.GraphicsContext;

public abstract class RectFigure extends FillableFigures{
    @Override
    public GraphicsContext redrawCanvas(GraphicsContext gc) {
        gc.fillRect(figure.getTopLeft().getX(), figure.getTopLeft().getY(), figure.getXDiameter(), figure.getYDiameter());
        gc.strokeRect(figure.getTopLeft().getX(), figure.getTopLeft().getY(), figure.getXDiameter(), figure.getYDiameter());
        return gc;
    }
}
