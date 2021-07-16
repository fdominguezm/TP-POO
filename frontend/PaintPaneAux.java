package frontend;

import backend.model.Figure;
import backend.model.Point;
import frontend.buttons.FigureButtonsList;
import frontend.formattedFigures.FormattedFigure;
import frontend.formattedFigures.FormattedFigureList;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
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
    ColorPicker figureColor = new ColorPicker(fillColor);
    ColorPicker borderColor = new ColorPicker(lineColor);
    ToggleButton deleteButton = new ToggleButton("Borrar");
    ToggleButton undoButton = new ToggleButton("Deshacer");
    ToggleButton redoButton = new ToggleButton("Rehacer");
    ToggleButton toBackButton = new ToggleButton("Al fondo");
    ToggleButton toFrontButton = new ToggleButton("Al frente");
    Slider borderThickness = new Slider(1,10,1);

    // Dibujar una figura
    Point startPoint;

    // BackEnd
    List<FormattedFigureList> canvasState = new ArrayList<>();
    int dim=0;
    // Seleccionar una figura
    //FormattedFigureList selectedFigures = new FormattedFigureList();

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

            if(selectionButton.isSelected()){
                    selectAllFigures(startPoint, endPoint); //selectedFigures.removeAll(selectedFigures) falta meter esto en este emtodo
            }else if(figureButtons.isSelected()){
                    FormattedFigure newFigure = figureButtons.createFigure(startPoint, endPoint, fillColor, lineColor, defaultThickness);
                    canvasState.get(dim).unselect();
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
                canvasState.get(dim).unselect();
                findFigure(event,label,"Ninguna figura encontrada");
                redrawCanvas();
            }
        });

        canvas.setOnMouseDragged(event -> {
            if(selectionButton.isSelected()) {
                Point eventPoint = new Point(event.getX(), event.getY());
                double diffX = (eventPoint.getX() - startPoint.getX()) / 100;
                double diffY = (eventPoint.getY() - startPoint.getY()) / 100;

                newCanvasState();
                for (FormattedFigure f: canvasState.get(dim)) {
                    if (f.isSelected()) {
                        f.move(diffX, diffY);
                    }
                }
                redrawCanvas();
            }
        });

        figureColor.setOnAction(event -> {
            newCanvasState();
            for (FormattedFigure f: canvasState.get(dim)) {
                if (f.isSelected()) {
                    f.unselect();
                    canvasState.get(dim).remove(f);
                    canvasState.get(dim).addFigure(f.setColor(figureColor.getValue()));
                }
            }
            redrawCanvas();
        });
        borderColor.setOnAction(event -> {
            newCanvasState();
            for (FormattedFigure f: canvasState.get(dim)) {
                if (f.isSelected()) {
                    f.unselect();
                    canvasState.get(dim).addFigure(f.setBorderColor(borderColor.getValue()));
                }
            }
            redrawCanvas();
        });
        deleteButton.setOnAction(event -> {
            newCanvasState();
            canvasState.get(dim).removeAll(canvasState.get(dim).selectedFigures());
            redrawCanvas();
        });
        undoButton.setOnAction(event -> {
            if(dim > 0) {
                dim--;
                redrawCanvas();
            }
        });
        redoButton.setOnAction(event -> {
            if (dim < canvasState.size() - 1) {
                dim++;
                redrawCanvas();
            }
        });
        toBackButton.setOnAction(event -> {
            newCanvasState();
            canvasState.get(dim).addAll(0,canvasState.get(dim).selectedFigures());
            redrawCanvas();
        });
        toFrontButton.setOnAction(event -> {
            newCanvasState();
            canvasState.get(dim).addAll(canvasState.get(dim).selectedFigures());
            redrawCanvas();
        });
        borderThickness.setOnMouseDragged(event-> {
            newCanvasState();
            for (FormattedFigure f : canvasState.get(dim)) {
                if (f.isSelected()) {
                    f.unselect();
                    canvasState.get(dim).add(f.setBorderThickness(borderThickness.getValue()));
                }
            }
            redrawCanvas();
        });

        setLeft(buttonsBox);
        setRight(canvas);
    }

    private void redrawCanvas() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (FormattedFigure figure : canvasState.get(dim)) {
            figure.redrawCanvas(gc);
        }
    }

    private void newCanvasState () {
        for (int i = dim+1; i<canvasState.size(); i++) {
            canvasState.remove(i);
        }
        FormattedFigureList aux = new FormattedFigureList();
        aux.addAll(canvasState.get(dim++));
        canvasState.add(aux);
    }

    private void selectAllFigures(Point startPoint, Point endPoint){

        for (FormattedFigure f: canvasState.get(dim)) {
            if(f.getFigure().belongs(startPoint,endPoint)){
                f.select();
            }
        }
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
        borderThickness.setShowTickLabels(true);
        borderThickness.setShowTickMarks(true);

        VBox buttonsBox = new VBox(10);
        buttonsBox.getChildren().addAll(toolsArr);
        Label fillText = new Label("Color de Relleno");
        buttonsBox.getChildren().add(fillText);
        buttonsBox.getChildren().add(figureColor);
        Label borderText = new Label("Color del Borde");
        buttonsBox.getChildren().add(borderText);
        buttonsBox.getChildren().add(borderColor);
        Label thicknessText = new Label("Grosor del Borde");
        buttonsBox.getChildren().add(thicknessText);
        buttonsBox.getChildren().add(borderThickness);
        buttonsBox.setPadding(new Insets(5));
        buttonsBox.setStyle("-fx-background-color: #999");
        buttonsBox.setPrefWidth(100);
        return buttonsBox;
    }

    private void findFigure(MouseEvent event, StringBuilder label, String s){
        Point eventPoint = new Point(event.getX(), event.getY());

        for (FormattedFigure figure : canvasState.get(dim)) {
            if(figureBelongs(figure.getFigure(), eventPoint)) {
                figure.select();
                label.append(figure.toString());
                statusPane.updateStatus(label.toString());
                return;
            }
        }

        if(canvasState.get(dim).selectedFigures().isEmpty()) {
            statusPane.updateStatus(s);
        }
    }

    private boolean figureBelongs(Figure figure, Point eventPoint) {
        return figure.belongs(eventPoint);
    }
}
