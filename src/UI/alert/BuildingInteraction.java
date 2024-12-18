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
import logic.building.CampingSite;
import logic.building.Farm;
import logic.building.base.Activatable;
import logic.building.base.Building;
import logic.building.misc.BuildingRuin;
import logic.building.misc.ConstructionSite;
import logic.building.misc.Demolishing;
import logic.building.misc.Tile;
import logic.utils.GameLogic;

public class BuildingInteraction extends VBox {
    public static void display(Tile tile, int i , int j){
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Choose your action");
        stage.setWidth(400);
        stage.setHeight(200);

        Building building = tile.getBuilding();

        VBox root = new VBox();
        root.setSpacing(15);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        HBox first = new HBox();
        //first.setPadding(new Insets(15));
        first.setSpacing(15);
        Text name = new Text(building.toString());
        name.setFont(Font.font(20));
        name.setTextAlignment(TextAlignment.LEFT);

        Text exploring = new Text("Exploring...");
        exploring.setFont(Font.font(20));
        exploring.setTextAlignment(TextAlignment.RIGHT);
        if (!(building instanceof CampingSite && ((CampingSite) building).isExploring())){
            exploring.setVisible(false);
        }

        first.getChildren().addAll(name, exploring);

        HBox second = new HBox();
        //second.setPadding(new Insets(15));
        second.setSpacing(15);
        Text status = new Text("");
        if (building instanceof Activatable){
            if (((Activatable) building).isActive()){
                status.setText("Status: Active");
            } else {
                status.setText("Status: Inactive");
            }
        }
        status.setFont(Font.font(15));
        status.setTextAlignment(TextAlignment.RIGHT);

        Button changeActiveStatus = new Button();
        changeActiveStatus.setAlignment(Pos.CENTER_RIGHT);
        changeActiveStatus.setFont(Font.font(15));
        //changeActiveStatus.setPrefHeight(43);
        if (!(building instanceof Activatable)){
            changeActiveStatus.setVisible(false);
        } else {
            if (((Activatable) building).isActive()){
                changeActiveStatus.setText("Deactivate");
            } else {
                changeActiveStatus.setText("Activate");
            }
        }
        changeActiveStatus.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GameLogic.getInstance().activateBuilding(tile);
                if (GameLogic.getInstance().inPlague()){
                    InPlague.display();
                }
                if (((Activatable) building).isActive()){
                    changeActiveStatus.setText("Deactivate");
                    status.setText("Status: Active");
                } else {
                    changeActiveStatus.setText("Activate");
                    status.setText("Status: Inactive");
                }
                IngameStage.updateInfo();
            }
        });

        second.getChildren().addAll(status, changeActiveStatus);

        HBox third = new HBox();
        third.setAlignment(Pos.CENTER);
        third.setSpacing(20);
        third.setPrefHeight(80);
        Button repairExplore = new Button();
        repairExplore.setFont(Font.font(15));
        //repairExplore.setPrefHeight(43);
        if (building instanceof BuildingRuin) {
            repairExplore.setText("Repair");
        }else if (building instanceof CampingSite) {
            if (((CampingSite) building).isExploring()){
                repairExplore.setDisable(true);
            }
            if (!((Activatable) building).isActive()){
                repairExplore.setDisable(true);
            }
            repairExplore.setText("Explore");
        } else {
            repairExplore.setVisible(false);
        }
        repairExplore.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (tile.getBuilding() instanceof CampingSite){
                    //TODO
                    boolean confirm = YesNoScreen.display();
                    if (confirm){
                        ((CampingSite) tile.getBuilding()).explore();
                        stage.close();
                    }
                }else if (tile.getBuilding() instanceof BuildingRuin){
                    //TODO

                    int price = GameLogic.getInstance().getBuildingCost().get(((BuildingRuin) tile.getBuilding()).getBuildingId()) / 2;
                    if (!GameLogic.haveEnoughMoney(price)){
                        //Not enough money
                        InsufficientAlert.display(1);
                        return;
                    }
                    else if (!GameLogic.haveEnoughBuilder()){
                        //Not enough builder
                        InsufficientAlert.display(0);
                        return;
                    }
                    boolean confirm = YesNoScreen.display();
                    if (confirm){
                        tile.setBuilding(new ConstructionSite(((BuildingRuin) tile.getBuilding()).getBuildingId()));
                        IngameStage.update(i, j, "MiscImage/RepairingRuin.png");
                        GameLogic.getInstance().getPlayer().subMoney(price);
                        GameLogic.getInstance().getPlayer().useBuilder();
                        IngameStage.updateInfo();
                        stage.close();
                    }
                }

            }
        });

        Button demolish = new Button("Demolish");
        demolish.setFont(Font.font(15));
        //demolish.setPrefHeight(43);
        demolish.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO
                if (!GameLogic.haveEnoughMoney(200)){
                    //Not enough money
                    InsufficientAlert.display(1);
                    return;
                }
                else if (!GameLogic.haveEnoughBuilder()){
                    //Not enough builder
                    InsufficientAlert.display(0);
                    return;
                }
                boolean confirm = YesNoScreen.display();
                if (confirm){
                    if (tile.getBuilding() instanceof Farm) {
                        GameLogic.getInstance().offFarm();
                    }
                    else if (tile.getBuilding() instanceof Activatable) {
                        if (((Activatable) tile.getBuilding()).isActive()){
                            ((Activatable) tile.getBuilding()).turnOn(false);
                        }
                    }
                    tile.setBuilding(new Demolishing());
                    IngameStage.update(i, j, tile.getBuilding().getUrl());
                    GameLogic.getInstance().getPlayer().subMoney(GameLogic.getInstance().getBuildingCost().get(8));
                    GameLogic.getInstance().getPlayer().useBuilder();
                    IngameStage.updateInfo();
                    stage.close();
                }

            }
        });

        Button cancel = new Button("Cancel");
        cancel.setFont(Font.font(15));
        //cancel.setPrefHeight(43);
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO
                stage.close();
            }
        });

        third.getChildren().addAll(repairExplore, demolish, cancel);

        root.getChildren().addAll(first, second, third);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait();
    }
}