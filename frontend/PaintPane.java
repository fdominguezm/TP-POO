package frontend;

import backend.CanvasState;
import backend.model.Circle;
import backend.model.Figure;
import backend.model.Point;
import backend.model.Rectangle;
import frontend.buttons.CircleButton;
import frontend.buttons.FigureButtons;
import frontend.buttons.RectangleButton;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.List;


public class PaintPane extends BorderPane {

    // BackEnd
    CanvasState canvasState;

    // Canvas y relacionados
    Canvas canvas = new Canvas(800, 600);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    Color lineColor = Color.BLACK;
    Color fillColor = Color.YELLOW;

    // Botones Barra Izquierda
    ToggleButton selectionButton = new ToggleButton("Seleccionar");
    FigureButtons rectangleButton = new RectangleButton("Rectángulo");
    FigureButtons circleButton = new CircleButton("Círculo");

    // Dibujar una figura
    Point startPoint;

    // Seleccionar una figura
    List<Figure> selectedFigures;

    // StatusBar
    StatusPane statusPane;

    public PaintPane(CanvasState canvasState, StatusPane statusPane) {
        this.canvasState = canvasState;
        this.statusPane = statusPane;

        VBox buttonsBox = setToggleButtons();

        gc.setLineWidth(1);

        canvas.setOnMousePressed(event -> {
            startPoint = new Point(event.getX(), event.getY());
        });
        canvas.setOnMouseReleased(event -> {

            /* //NUEVO RELEASED
            Point endPoint = new Point(event.getX(), event.getY());
            if(startPoint == null){
                return;
            }

            Figure newFigure = null;

            if(selectButton.isSelected()){
                    selectAllFigures(startPoint, endPoint, selectedFigures);
            }else if(figureButtons.isSelected()){
                    newFigure = figureButtons.createFigure(startPoint, endPoint);
                    canvasState.addFigure(newFigure); // En addFigure chequear distinto de null
            }

            startPoint = null;
            redrawCanvas();

             */

            Point endPoint = new Point(event.getX(), event.getY());
            if(startPoint == null || (endPoint.getX() < startPoint.getX() || endPoint.getY() < startPoint.getY())) {
                return ;
            }

            Figure newFigure = null;

            if(rectangleButton.isSelected()) {
                newFigure = new Rectangle(startPoint, endPoint);
            }
            else if(circleButton.isSelected()) {
                double circleRadius = Math.abs(endPoint.getX() - startPoint.getX());
                newFigure = new Circle(startPoint, circleRadius);
            } else {
                return ;
            }
            canvasState.addFigure(newFigure);
            startPoint = null;
            redrawCanvas();
        });


        canvas.setOnMouseMoved(event -> {
            Point eventPoint = new Point(event.getX(), event.getY());
            StringBuilder label = new StringBuilder();
            if(findFigure(event,label) == null) {
                statusPane.updateStatus(eventPoint.toString());
            }
        });

        canvas.setOnMouseClicked(event -> {
            if(selectionButton.isSelected()) {
                StringBuilder label = new StringBuilder("Se seleccionó: ");
                selectedFigures = findFigure(event,label);
                if(selectedFigures == null) {
                    statusPane.updateStatus("Ninguna figura encontrada");
                }
                redrawCanvas();
            }
        });
        canvas.setOnMouseDragged(event -> {
            if(selectionButton.isSelected()) {
                Point eventPoint = new Point(event.getX(), event.getY());
                double diffX = (eventPoint.getX() - startPoint.getX()) / 100;
                double diffY = (eventPoint.getY() - startPoint.getY()) / 100;
                selectedFigure.move(diffX, diffY);
                redrawCanvas();
            }
        });
        setLeft(buttonsBox);
        setRight(canvas);
    }

    private void redrawCanvas() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for(Figure figure : canvasState.figures()) {
            if(figure == selectedFigures) {
                gc.setStroke(Color.RED);
            } else {
                gc.setStroke(lineColor);
            }
            gc.setFill(fillColor);
            if(figure instanceof Rectangle) {
                Rectangle rectangle = (Rectangle) figure;
                gc.fillRect(rectangle.getTopLeft().getX(), rectangle.getTopLeft().getY(),
                        Math.abs(rectangle.getTopLeft().getX() - rectangle.getBottomRight().getX()), Math.abs(rectangle.getTopLeft().getY() - rectangle.getBottomRight().getY()));
                gc.strokeRect(rectangle.getTopLeft().getX(), rectangle.getTopLeft().getY(),
                        Math.abs(rectangle.getTopLeft().getX() - rectangle.getBottomRight().getX()), Math.abs(rectangle.getTopLeft().getY() - rectangle.getBottomRight().getY()));
            } else if(figure instanceof Circle) {
                Circle circle = (Circle) figure;
                double diameter = circle.getRadius() * 2;
                gc.fillOval(circle.getCenterPoint().getX() - circle.getRadius(), circle.getCenterPoint().getY() - circle.getRadius(), diameter, diameter);
                gc.strokeOval(circle.getCenterPoint().getX() - circle.getRadius(), circle.getCenterPoint().getY() - circle.getRadius(), diameter, diameter);
            }
        }
    }

    private boolean figureBelongs(Figure figure, Point eventPoint) {
        return figure.belongs(eventPoint);
    }

    private Figure findFigure(MouseEvent event,StringBuilder label){
        Point eventPoint = new Point(event.getX(), event.getY());
        for (Figure figure : canvasState.figures()) {
            if(figureBelongs(figure, eventPoint)) {
                label.append(figure.toString());
                statusPane.updateStatus(label.toString());
                return figure;
            }
        }
        return null;
    }

    private VBox setToggleButtons(){
        ToggleButton[] toolsArr = {selectionButton, rectangleButton, circleButton};
        ToggleGroup tools = new ToggleGroup();
        for (ToggleButton tool : toolsArr) {
            tool.setMinWidth(90);
            tool.setToggleGroup(tools);
            tool.setCursor(Cursor.HAND);
        }
        VBox buttonsBox = new VBox(10);
        buttonsBox.getChildren().addAll(toolsArr);
        buttonsBox.setPadding(new Insets(5));
        buttonsBox.setStyle("-fx-background-color: #999");
        buttonsBox.setPrefWidth(100);
        return buttonsBox;
    }

}
