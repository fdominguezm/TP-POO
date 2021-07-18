package frontend;

import backend.model.Figure;
import backend.model.Point;
import frontend.buttons.FigureButtonsList;
import frontend.formattedFigures.FormattedFigure;
import frontend.formattedFigures.FormattedFigureList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
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
    Slider borderThickness = new Slider(1,50,1);

    // Dibujar una figura
    Point startPoint;

    CanvasState canvasState = new CanvasState();

    StatusPane statusPane;;

    public PaintPaneAux(StatusPane statusPane){
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
            if (figureButtons.isSelected()) {
                canvasState.addFigure(figureButtons.createFigure(startPoint, endPoint, fillColor, lineColor, defaultThickness));
            } else if(canvasState.state().selectedFigures().isEmpty()) {
                selectAllFigures (startPoint, endPoint);

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
                //canvasState.get(dim).unselect();
                FormattedFigure f = findFigure(event,label,"Ninguna figura encontrada");
                if (f != null){
                    f.select();
                }
                redrawCanvas();
            }
        });

        canvas.setOnMouseDragged(event -> {
            if(selectionButton.isSelected()) {
                Point eventPoint = new Point(event.getX(), event.getY());
                double diffX = (eventPoint.getX() - startPoint.getX()) / 100;
                double diffY = (eventPoint.getY() - startPoint.getY()) / 100;

                for (FormattedFigure f: canvasState.state()) {
                    if (f.isSelected()) {
                        f.move(diffX, diffY);
                    }
                }
                redrawCanvas();
            }
        });

        figureColor.setOnAction(event -> {
            canvasState.apply(f-> {
                return f.setColor(figureColor.getValue());
            });
            redrawCanvas();
        });
        borderColor.setOnAction(event -> {
            canvasState.apply(f-> {
                return f.setBorderColor(borderColor.getValue());
            });
            redrawCanvas();
        });
        deleteButton.setOnAction(event -> {
            canvasState.apply(f -> {return null;});
            redrawCanvas();
        });
        undoButton.setOnAction(event -> {
            canvasState.undo();
            redrawCanvas();
        });
        redoButton.setOnAction(event -> {
            canvasState.redo();
            redrawCanvas();
        });
        toBackButton.setOnAction(event -> {
            canvasState.toBack();
            redrawCanvas();
        });
        toFrontButton.setOnAction(event -> {
            canvasState.apply(f->f);
            redrawCanvas();
        });


        borderThickness.setShowTickLabels(true);
        borderThickness.setShowTickMarks(true);
        borderThickness.setBlockIncrement(5);

        //Linking the transformation to the slider
        EventHandler<MouseEvent> sliderEvent = mouseEvent -> {
            FormattedFigureList aux = new FormattedFigureList();
            for (FormattedFigure f : canvasState.state()) {
                if (f.isSelected()) {
                    aux.add(f.setBorderThickness(borderThickness.getValue()));
                }
            }
            canvasState.state().addAll(aux);
            redrawCanvas();

        };

        borderThickness.setOnMouseDragged(sliderEvent);
        borderThickness.setOnMouseClicked(sliderEvent);

        setLeft(buttonsBox);
        setRight(canvas);
    }

    private void redrawCanvas() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (FormattedFigure figure : canvasState.state()) {
            figure.redrawCanvas(gc);
        }
    }

    private void selectAllFigures(Point startPoint, Point endPoint){

        for (FormattedFigure f: canvasState.state()) {
            if(f.belongs(startPoint,endPoint)){
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

    private FormattedFigure findFigure(MouseEvent event, StringBuilder label, String s){
        Point eventPoint = new Point(event.getX(), event.getY());
        FormattedFigure f = null;

        for (FormattedFigure figure : canvasState.state()) {
            if(figureBelongs(figure.getFigure(), eventPoint)) {
                f =figure;
                label.append(figure.toString());
                statusPane.updateStatus(label.toString());
            }
        }

        if(canvasState.state().selectedFigures().isEmpty()) {
            statusPane.updateStatus(s);
        }
        return f;
    }

    //ESTE METODO CREO Q ESTA AL PEDO
    private boolean figureBelongs(Figure figure, Point eventPoint) {
        return figure.belongs(eventPoint);
    }
}
