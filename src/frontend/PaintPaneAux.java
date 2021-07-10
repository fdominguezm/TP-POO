package frontend;

import backend.CanvasState;
import backend.model.Figure;
import backend.model.Point;
import frontend.buttons.FigureButtons;
import frontend.buttons.FigureButtonsList;
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

import java.util.ArrayList;
import java.util.List;

public class PaintPaneAux  extends BorderPane {
    // BackEnd
    CanvasState canvasState;

    // Canvas y relacionados
    Canvas canvas = new Canvas(800, 600);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    Color lineColor = Color.BLACK;
    Color fillColor = Color.YELLOW;

    ToggleButton selectionButton = new ToggleButton("Seleccionar");
    FigureButtonsList figureButtons = new FigureButtonsList();

    // Dibujar una figura
    Point startPoint;

    // Seleccionar una figura
    List<Figure> selectedFigures;

    // StatusBar
    StatusPane statusPane;

    public PaintPaneAux(CanvasState canvasState, StatusPane statusPane){
        this.canvasState = canvasState;
        this.statusPane = statusPane;

        VBox buttonsBox = setToggleButtons();

        gc.setLineWidth(1);

        canvas.setOnMousePressed(event -> {
            startPoint = new Point(event.getX(), event.getY());
        });

        canvas.setOnMouseReleased(event -> {

            //NUEVO RELEASED
            Point endPoint = new Point(event.getX(), event.getY());
            if(startPoint == null){
                return;
            }

            Figure newFigure = null;

            if(selectionButton.isSelected()){
                    selectAllFigures(startPoint, endPoint, selectedFigures); //selectedFigures.removeAll(selectedFigures) falta meter esto en este emtodo
            }else if(figureButtons.isSelected()){
                    newFigure = figureButtons.createFigure(startPoint, endPoint);
                    canvasState.addFigure(newFigure); // En addFigure chequear distinto de null
            }

            startPoint = null;
            redrawCanvas();
        });

        canvas.setOnMouseMoved(event -> {
            Point eventPoint = new Point(event.getX(), event.getY());
            StringBuilder label = new StringBuilder();
            findFigure(event,label,eventPoint.toString());
        });

        canvas.setOnMouseClicked(event -> {
            if(selectionButton.isSelected()) {
                StringBuilder label = new StringBuilder("Se seleccionó: ");
                selectedFigures.removeAll(selectedFigures);
                findFigure(event,label,"Ninguna figura encontrada");
                redrawCanvas();
            }
        });

        canvas.setOnMouseDragged(event -> {
            if(selectionButton.isSelected()) {
                Point eventPoint = new Point(event.getX(), event.getY());
                double diffX = (eventPoint.getX() - startPoint.getX()) / 100;
                double diffY = (eventPoint.getY() - startPoint.getY()) / 100;
                for (Figure f: selectedFigures) {
                    f.move(diffX,diffY);
                }
                redrawCanvas();
            }
        });

        setLeft(buttonsBox);
        setRight(canvas);
    }

    private void redrawCanvas() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (Figure figure : canvasState.figures()) {
            if (selectedFigures.contains(figure)) {
                gc.setStroke(Color.RED);
            } else {
                gc.setStroke(lineColor);
            }
            gc.setFill(fillColor);
            gc = figureButtons.redrawCanvas(gc);
        }
    }



    private VBox setToggleButtons(){
        List<ToggleButton> toolsArr = new ArrayList<>();
        toolsArr.add(selectionButton);
        toolsArr.addAll(figureButtons);
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

    private void findFigure(MouseEvent event, StringBuilder label, String s){
        Point eventPoint = new Point(event.getX(), event.getY());
        for (Figure figure : canvasState.figures()) {
            if(figureBelongs(figure, eventPoint)) {
                selectedFigures.add(figure);
                label.append(figure.toString());
                statusPane.updateStatus(label.toString());
                return;
            }
        }

        if(selectedFigures.isEmpty()) {
            statusPane.updateStatus(s);
        }
    }

    private boolean figureBelongs(Figure figure, Point eventPoint) {
        return figure.belongs(eventPoint);
    }
}
