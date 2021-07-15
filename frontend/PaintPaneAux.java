package frontend;

import backend.model.Figure;
import backend.model.Point;
import frontend.buttons.FigureButtonsList;
import frontend.formattedFigures.FormattedFigure;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PaintPaneAux  extends BorderPane {

    // Canvas y relacionados
    Canvas canvas = new Canvas(800, 600);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    Color lineColor = Color.BLACK;
    Color fillColor = Color.YELLOW;
    Double defaultThickness = 1.0;

    ToggleButton selectionButton = new ToggleButton("Seleccionar");
    FigureButtonsList figureButtons = new FigureButtonsList();
    ColorPicker figureColor = new ColorPicker();
    ColorPicker borderColor = new ColorPicker();
    ToggleButton deleteButton = new ToggleButton("Delete");
    ToggleButton undoButton = new ToggleButton("Undo");
    ToggleButton redoButton = new ToggleButton("Redo");
    ToggleButton toBackButton = new ToggleButton("Back");
    ToggleButton toFrontButton = new ToggleButton("Front");
    Slider borderThickness = new Slider();

    // Dibujar una figura
    Point startPoint;

    // BackEnd
    List<FormattedFigureList> canvasState = new ArrayList<>();
    int dim=0;
    // Seleccionar una figura
    FormattedFigureList selectedFigures = new FormattedFigureList();

    // StatusBar
    StatusPane statusPane;

    public PaintPaneAux(StatusPane statusPane){
        canvasState.add(new FormattedFigureList());
        this.statusPane = statusPane;

        VBox buttonsBox = setToggleButtons();

        gc.setLineWidth(defaultThickness);

        canvas.setOnMousePressed(event -> {
            startPoint = new Point(event.getX(), event.getY());
        });

        canvas.setOnMouseReleased(event -> {
            Point endPoint = new Point(event.getX(), event.getY());
            if(startPoint == null){
                return;
            }
            newCanvasState();
            selectedFigures.removeAll(selectedFigures);
            if(selectionButton.isSelected()){
                    selectedFigures = selectAllFigures(startPoint, endPoint); //selectedFigures.removeAll(selectedFigures) falta meter esto en este emtodo
            }else if(figureButtons.isSelected()){
                    FormattedFigure newFigure = figureButtons.createFigure(startPoint, endPoint, fillColor, lineColor, defaultThickness);
                    canvasState.get(dim).addFigure(newFigure); // En addFigure chequear distinto de null
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
                                                                                //eliminar el move del backend
                newCanvasState();
                for (FormattedFigure f: selectedFigures) {
                    canvasState.get(dim).add(f.move(diffX,diffY));
                }
                redrawCanvas();
            }
        });

        figureColor.setOnAction(event -> {
            newCanvasState();
            for (FormattedFigure f: selectedFigures) {
                canvasState.get(dim).add(f.setColor(figureColor.getValue()));
            }
            redrawCanvas();
        });
        borderColor.setOnAction(event -> {
            newCanvasState();
            for (FormattedFigure f: selectedFigures) {
                canvasState.get(dim).add(f.setBorderColor(borderColor.getValue()));
            }
            redrawCanvas();
        });
        deleteButton.setOnAction(event -> {
            newCanvasState();
            canvasState.get(dim).removeAll(selectedFigures);
            redrawCanvas();
        });
        undoButton.setOnAction(event -> {
            dim--;
            redrawCanvas();
        });
        redoButton.setOnAction(event -> {
            if (dim < canvasState.size()) {
                dim++;
                redrawCanvas();
            }
        });
        toBackButton.setOnAction(event -> {
            newCanvasState();
            canvasState.get(dim).addAll(0,selectedFigures);
            redrawCanvas();
        });
        toFrontButton.setOnAction(event -> {
            newCanvasState();
            canvasState.get(dim).addAll(selectedFigures);
            redrawCanvas();
        });
        borderThickness.setOnMouseDragged(event-> {
            newCanvasState();
            for (FormattedFigure f : selectedFigures) {
                canvasState.get(dim).add(f.setBorderThickness(borderThickness.getValue()));
            }
            redrawCanvas();
        });

        setLeft(buttonsBox);
        setRight(canvas);
    }

    private void redrawCanvas() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (FormattedFigure figure : canvasState.get(dim)) {
            if (selectedFigures.contains(figure)) {
                gc.setStroke(Color.RED);
            } else {
                gc.setStroke(lineColor);
            }
            gc.setFill(fillColor);
            figure.redrawCanvas(gc);
        }
    }

    private void newCanvasState () {
        for (int i = dim+1; i<canvasState.size(); i++) {
            canvasState.remove(i);
        }
        FormattedFigureList aux = new FormattedFigureList();
        aux.removeAll(selectedFigures);
        aux.addAll(canvasState.get(dim++));
        canvasState.add(aux);
    }

    private FormattedFigureList selectAllFigures(Point startPoint, Point endPoint){
        selectedFigures.removeAll(selectedFigures);
        for (FormattedFigure f: canvasState.get(dim)) {
            if(f.getFigure().belongs(startPoint,endPoint)){
                selectedFigures.addFigure(f);
            }
        }
        return selectedFigures;
    }

    private VBox setToggleButtons(){
        List<ToggleButton> toolsArr = new ArrayList<>();
        ToggleButton[] aux = {deleteButton, undoButton, redoButton, toBackButton, toFrontButton};
        toolsArr.add(selectionButton);
        toolsArr.addAll(figureButtons);
        toolsArr.addAll(Arrays.asList(aux));
        ToggleGroup tools = new ToggleGroup();
        for (ToggleButton tool : toolsArr) {
            tool.setMinWidth(90);
            tool.setToggleGroup(tools);
            tool.setCursor(Cursor.HAND);
        }
        VBox buttonsBox = new VBox(10);
        buttonsBox.getChildren().addAll(toolsArr);
        buttonsBox.getChildren().add(figureColor);
        buttonsBox.getChildren().add(borderColor);
        buttonsBox.getChildren().add(borderThickness);
        buttonsBox.setPadding(new Insets(5));
        buttonsBox.setStyle("-fx-background-color: #999");
        buttonsBox.setPrefWidth(100);
        return buttonsBox;
    }

    private void findFigure(MouseEvent event, StringBuilder label, String s){
        Point eventPoint = new Point(event.getX(), event.getY());
        for (FormattedFigure figure : canvasState.get(dim).figures()) {
            if(figureBelongs(figure.getFigure(), eventPoint)) {
                selectedFigures.addFigure(figure);
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
