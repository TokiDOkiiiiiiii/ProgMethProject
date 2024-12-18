package UI.component;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import logic.utils.GameLogic;


public class TileButton extends StackPane {
    private int row;
    private int col;

    private ImageView building;

    public TileButton(int row, int col){
        setRow(row);
        setCol(col);
        StackPane tile = tileImage();
        building = new ImageView();
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //BuyMenu.display(getRow(), getCol());
                GameLogic.selectTile(getRow(), getCol());
            }
        });
        this.getChildren().addAll(tile, building);
    }

    public void updateImage(String url){
        try{
            Image image = new Image(ClassLoader.getSystemResourceAsStream(url));
            building.setFitHeight(130);
            building.setFitWidth(130);
            building.setImage(image);
        }
        catch(Exception e){
            //System.out.println("not found");
            building.setImage(null);
        }
    }


    public StackPane tileImage(){
        StackPane root = new StackPane();
        String tileImageUrl = "tile3.png";
        try{
            Image tileImage = new Image(ClassLoader.getSystemResourceAsStream(tileImageUrl));
            ImageView tileImageView = new ImageView(tileImage);
            tileImageView.setFitHeight(130);
            tileImageView.setFitWidth(130);
            root.getChildren().add(tileImageView);
        }
        catch(Exception e){
        }

        return root;
    }


    public void setRow(int row){
        this.row = row;
    }

    public void setCol(int col){
        this.col = col;
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }


}
