package UI.alert;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.utils.GameLogic;

public class MercenaryAlert {
    public static void display(int power, int price){
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);


        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Mercenary");
        stage.setWidth(640);
        stage.setHeight(460);

        VBox root = new VBox();
        HBox btnPane = new HBox();
        btnPane.setAlignment(Pos.CENTER);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(30));
        root.setSpacing(30);


        try{
            Image image = new Image(ClassLoader.getSystemResourceAsStream("EventIcon/MercenaryEventIcon.png"));
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(150);
            imageView.setFitWidth(150);
            root.getChildren().add(imageView);
        }
        catch(Exception e){

        }

        Text desc = new Text("Do you want to buy " + power + " power for " + price + " gold");
        desc.setFont(Font.font(15));

        Button yes = new Button("YES");
        Button no = new Button("NO");
        yes.setAlignment(Pos.CENTER);
        no.setAlignment(Pos.CENTER);
        yes.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                boolean confirm = YesNoScreen.display();
                if (!GameLogic.haveEnoughMoney(price)){
                    //Not enough money window
                    InsufficientAlert.display(1);
                    return;
                }
                else if (confirm){
                        GameLogic.getInstance().getPlayer().subMoney(price);
                        GameLogic.getInstance().getPlayer().addPower(power);
                        stage.close();
                }

            }
        });
        no.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.close();
            }
        });
        yes.setPrefSize(50, 30);
        no.setPrefSize(50, 30);
        yes.setFont(Font.font(15));
        no.setFont(Font.font(15));
        btnPane.getChildren().addAll(yes, no);
        btnPane.setSpacing(30);

        root.getChildren().addAll(desc, btnPane);

        stage.setScene(new Scene(root));

        stage.setTitle("Mercenary Event");
        stage.setResizable(false);
        stage.showAndWait();
    }
}
