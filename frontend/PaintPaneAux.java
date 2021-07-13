package frontend;

import backend.model.Figure;
import backend.model.Point;
import frontend.buttons.FigureButtonsList;
import frontend.formattedFigures.FillableFigures;
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

            FormattedFigure newFigure = null;

            if(selectionButton.isSelected()){
                    selectedFigures = selectAllFigures(startPoint, endPoint); //selectedFigures.removeAll(selectedFigures) falta meter esto en este emtodo
            }else if(figureButtons.isSelected()){
                    newFigure = figureButtons.createFigure(startPoint, endPoint);
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

                for (FormattedFigure f: selectedFigures) {
                    f.getFigure().move(diffX,diffY);
                }
                redrawCanvas();
            }
        });

        figureColor.setOnAction(event -> {
            for (FormattedFigure f: selectedFigures) {
                f.setColor(figureColor.getValue());
            }
        });
        borderColor.setOnAction(event -> {
            for (FormattedFigure f: selectedFigures) {
                f.setBorderColor(figureColor.getValue());
            }
        });
        deleteButton.setOnAction(event -> {
            canvasState.get(dim).removeAll(selectedFigures);
        });
        undoButton.setOnAction(event -> {

        });
        redoButton.setOnAction(event -> {
            //hay que ver
        });
        toBackButton.setOnAction(event -> {
            canvasState.get(dim).removeAll(selectedFigures);
            canvasState.get(dim).addAll(0,selectedFigures);
        });
        toFrontButton.setOnAction(event -> {
            canvasState.get(dim).removeAll(selectedFigures);
            canvasState.get(dim).addAll(selectedFigures);
        });


        setLeft(buttonsBox);
        setRight(canvas);
    }

    private void redrawCanvas() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (FormattedFigure figure : canvasState.get(dim).figures()) {
            if (selectedFigures.contains(figure)) {
                gc.setStroke(Color.RED);
            } else {
                gc.setStroke(lineColor);
            }
            gc.setFill(fillColor);

            gc = selectedFigures.get(0).redrawCanvas(gc);
        }
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
