package UI.alert;
import UI.pane.IngameStage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.utils.GameLogic;

public class TownHallUpgrade{
    public static void display() {
        Stage stage = new Stage();


        int price = GameLogic.getInstance().getTownHall().getLevel() * 1000;

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setWidth(400);
        stage.setHeight(200);

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20, 0, 0, 0));

        Text name = new Text("Upgrade " + GameLogic.getInstance().getTownHall().toString() + " for " + price + " golds?");
        name.setFont(Font.font(15));

        HBox first = new HBox();
        //first.setPadding(new Insets(15));
        root.setSpacing(50);
        first.setAlignment(Pos.CENTER);
        first.setSpacing(150);
        Button upgrade = new Button("Upgrade");
        //upgrade.setPrefSize(50,30);
        upgrade.setFont(Font.font(15));
        upgrade.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO
                if (!GameLogic.haveEnoughMoney(price)){
                    InsufficientAlert.display(1);
                    return;
                }
                else if (!GameLogic.haveEnoughBuilder()){
                    InsufficientAlert.display(0);
                    return;
                }
                boolean confirm = YesNoScreen.display();
                if (confirm){
                    String url = GameLogic.getInstance().getTownHall().buyUpgrade();
                    IngameStage.update(2,2,url);
                    GameLogic.getInstance().getPlayer().subMoney(price);
                    GameLogic.getInstance().getPlayer().useBuilder();
                    IngameStage.updateInfo();
                    stage.close();
                }

            }
        });

        Button cancel = new Button("Cancel");
        cancel.setFont(Font.font(15));
        //cancel.setPrefSize(60,30);
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });
        first.getChildren().addAll(upgrade, cancel);

        root.getChildren().addAll(name, first);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Town Hall Upgrade");
        stage.setResizable(false);
        stage.showAndWait();
    }
}