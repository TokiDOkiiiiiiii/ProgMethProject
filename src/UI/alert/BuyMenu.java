package UI.alert;

import UI.component.BuildingChoiceBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BuyMenu {
    
    public static Stage stage;
    public static int curRow;
    public static int curCol;
    public static void display(int row, int col){
        curRow = row;
        curCol = col;
        stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Buy Building");
        stage.setWidth(640);
        stage.setHeight(460);

        GridPane buyMenu = new GridPane();
        buyMenu.setPadding(new Insets(30));
        buyMenu.setVgap(15);
        buyMenu.setHgap(30);
        buyMenu.setAlignment(Pos.CENTER);
        buyMenu.add(new BuildingChoiceBox(1, "Farm", "BuildingImage/Farm.png", 200),0 , 0);
        buyMenu.add(new BuildingChoiceBox(2, "ArmyCamp", "BuildingImage/ArmyCamp.png", 300),1 , 0);
        buyMenu.add(new BuildingChoiceBox(3, "Temple", "BuildingImage/Temple.png", 500),2 , 0);
        buyMenu.add(new BuildingChoiceBox(4, "CampSite", "BuildingImage/CampingSite.png", 500),0 , 1);
        buyMenu.add(new BuildingChoiceBox(5, "ArmySchool", "BuildingImage/ArmySchool.png", 700),1 , 1);
        buyMenu.add(new BuildingChoiceBox(6, "FarmSchool", "BuildingImage/FarmSchool.png", 700),2 , 1);


        Scene scene = new Scene(buyMenu);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait();

    }
    
    public static void stageClose(){
        stage.close();
    }

}
