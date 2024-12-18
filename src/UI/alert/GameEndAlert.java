package UI.alert;

import application.Main;
import UI.pane.GameLoop;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import logic.utils.GameLogic;

public class GameEndAlert{
    public static void display(String name, String des){
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(name);
        stage.setWidth(640);
        stage.setHeight(400);

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30, 30, 30, 30));
        root.setSpacing(50);
        Button close = new Button("EXIT");
        close.setPrefSize(50,30);
        close.setFont(Font.font(15));

        Text gameOverText = new Text("Game Over!");
        gameOverText.setFont(Font.font(60));

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                GameLoop.st.setScene(Main.startScene);
                GameLogic.killInstance();
                stage.close();
            }
        });



        close.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                GameLogic.killInstance();
                GameLoop.st.setScene(Main.startScene);
                stage.close();
            }
        });

        Text message = new Text(des);
        message.setFont(Font.font(20));
        message.setTextAlignment(TextAlignment.CENTER);

        Text ending = new Text("You are " + name);
        ending.setFont(Font.font(40));

        root.getChildren().addAll(gameOverText,message,ending, close);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait();
    }
}