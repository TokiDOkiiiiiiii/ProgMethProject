package UI.pane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StartGame extends VBox {
    private Stage stage;
    public StartGame(Stage stage){
        this.stage = stage;
        this.setPrefHeight(720);
        this.setPrefWidth(1280);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(100);
        this.setStyle("-fx-background-image: url(\"BackGroundEdited.png\"); -fx-background-size: 1280 720;");
        Text title = new Text("Game Sang Mueang");
        title.setFont(Font.font(96));
        Button start = new Button("Start");
        start.setFont(Font.font(50));
        start.setPrefWidth(370);
        start.setPrefHeight(100);
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO
                GameLoop.startGame(stage);
            }
        });

        Button quit = new Button("Quit");
        quit.setFont(Font.font(50));
        quit.setPrefWidth(370);
        quit.setPrefHeight(100);
        quit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
            }
        });

        this.getChildren().addAll(title, start, quit);
    }
}