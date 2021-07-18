package frontend;

import javafx.scene.layout.VBox;

public class MainFrame extends VBox {

    public MainFrame() {
        getChildren().add(new AppMenuBar());
        StatusPane statusPane = new StatusPane();
        getChildren().add(new PaintPaneAux(statusPane));
        getChildren().add(statusPane);
    }

}