package UI.alert;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InsufficientAlert {
    public static void display(int i) {
        Stage stage = new Stage();
        String name = "Insufficient Builder";
        String message = "You do not have enough builder";
        if (i == 1){
            name = "Insufficient Money";
            message = "You do not have enough money";
        }
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(name);
        stage.setWidth(300);
        stage.setHeight(150);

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(30);
        Text text = new Text(message);
        text.setFont(Font.font(15));

        Button close = new Button("OK");
        close.setPrefSize(50,30);
        close.setFont(Font.font(15));
        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });

        root.getChildren().addAll(text, close);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait();
    }
}
