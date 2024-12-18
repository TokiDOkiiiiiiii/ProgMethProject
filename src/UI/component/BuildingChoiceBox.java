package UI.component;

import UI.alert.BuyMenu;
import UI.alert.InsufficientAlert;
import UI.alert.YesNoScreen;
import UI.pane.IngameStage;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import logic.building.misc.ConstructionSite;
import logic.utils.GameLogic;

public class BuildingChoiceBox extends VBox {
    public BuildingChoiceBox(int id, String name, String url, int price){
        setAlignment(Pos.CENTER);
        Text buildingName = new Text(name + " : " + price + " golds");
        buildingName.setFont(Font.font(15));
        buildingName.setTextAlignment(TextAlignment.CENTER);
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //BuyMenu.stageClose();
                if (!GameLogic.haveEnoughMoney(price)){
                    //Not enough money window
                    InsufficientAlert.display(1);
                    return;
                }
                else if (!GameLogic.haveEnoughBuilder()){
                    //Not enough builder window
                    InsufficientAlert.display(0);
                    return;
                }
                boolean confirm = YesNoScreen.display();
                if (confirm){
                    IngameStage.update(BuyMenu.curRow, BuyMenu.curCol, "MiscImage/ConstructionSite.png");
                    GameLogic.getInstance().getMap().get(BuyMenu.curRow).get(BuyMenu.curCol).setBuilding(new ConstructionSite(id));
                    GameLogic.getInstance().getPlayer().subMoney(price);
                    GameLogic.getInstance().getPlayer().useBuilder();
                    IngameStage.updateInfo();
                    BuyMenu.stageClose();
                }

            }
        });

        try{
            Image image = new Image(ClassLoader.getSystemResourceAsStream(url));
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(150);
            imageView.setFitWidth(150);
            this.getChildren().addAll(imageView, buildingName);
        }
        catch(Exception e){
        }
    }
}
