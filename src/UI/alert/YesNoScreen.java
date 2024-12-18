package UI.alert;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class YesNoScreen {
    public static boolean display(){
        final boolean[] answer = {false};
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Confirmation");
        stage.setWidth(300);
        stage.setHeight(150);

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(50, 30, 0, 30));
        HBox pane = new HBox();
        pane.setSpacing(30);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(30));
        Button yes = new Button("YES");
        Button no = new Button("NO");
        yes.setPrefSize(50,30);
        no.setPrefSize(50,30);
        yes.setFont(Font.font(15));
        no.setFont(Font.font(15));


        yes.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                answer[0] = true;
                stage.close();

            }
        });

        no.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                answer[0] = false;
                stage.close();
            }
        });

        pane.getChildren().addAll(yes, no);

        Text text = new Text("Are You sure?");
        text.setFont(Font.font(15));
        text.setTextAlignment(TextAlignment.CENTER);

        root.getChildren().addAll(text, pane);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait();
        return answer[0];
    }
}
