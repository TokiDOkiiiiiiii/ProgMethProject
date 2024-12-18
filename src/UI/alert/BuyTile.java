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
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.building.misc.Tile;
import logic.utils.GameLogic;

public class BuyTile {
    public static void display(Tile tile, int i, int j) {
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setWidth(400);
        stage.setHeight(200);

        VBox root = new VBox();
        root.setSpacing(50);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20, 0, 0, 0));

        Text name = new Text("Buy this tile for 300 golds?");
        name.setTextAlignment(TextAlignment.CENTER);
        name.setFont(Font.font(15));

        HBox first = new HBox();
        //first.setPadding(new Insets(15));
        first.setAlignment(Pos.CENTER);
        first.setSpacing(150);
        Button upgrade = new Button("Buy");
        //upgrade.setPrefSize(50,30);
        upgrade.setFont(Font.font(15));
        upgrade.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO
                int price = GameLogic.getInstance().getBuildingCost().get(7);
                if (!GameLogic.haveEnoughMoney(price)){
                    InsufficientAlert.display(1);
                    return;
                }
                else if (!GameLogic.haveEnoughBuilder()){
                    InsufficientAlert.display(0);
                    return;
                }
                boolean confirm = YesNoScreen.display();
                if (confirm) {
                    tile.setClearingArea(true);
                    IngameStage.update(i,j,"MiscImage/Deforestation.png");
                    GameLogic.getInstance().getPlayer().subMoney(price);
                    GameLogic.getInstance().getPlayer().useBuilder();
                    IngameStage.updateInfo();
                    stage.close();
                }

            }
        });

        Button cancel = new Button("Cancel");
        cancel.setFont(Font.font(15));
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
        stage.setTitle("Buy Tile");
        stage.setResizable(false);
        stage.showAndWait();
    }
}