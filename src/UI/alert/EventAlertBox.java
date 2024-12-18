package UI.alert;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EventAlertBox {
    public static void display(String eventName, String eventDescription, String URL){
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(eventName);
        stage.setWidth(640);
        stage.setHeight(460);

        VBox root = new VBox();
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(30));
        root.setSpacing(30);

        try{
            Image image = new Image(ClassLoader.getSystemResourceAsStream(URL));
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(150);
            imageView.setFitWidth(150);
            root.getChildren().add(imageView);
        }
        catch(Exception e){
        }

        Text desc = new Text(eventDescription);
        desc.setTextAlignment(TextAlignment.CENTER);
        desc.setFont(Font.font(15));

        Button okBtn = new Button("OK");
        okBtn.setAlignment(Pos.CENTER);
        okBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.close();
            }
        });
        okBtn.setPrefSize(50, 30);
        okBtn.setFont(Font.font(15));
        root.getChildren().addAll(desc, okBtn);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait();
    }
}
