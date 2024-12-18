package UI.pane;

import UI.component.TileButton;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import logic.utils.GameLogic;

import java.util.ArrayList;


public class IngameStage extends BorderPane {

    private GridPane playArea;
    public static ArrayList<TileButton> map;
    private VBox displayArea;

    private static Text day;
    private static Text money;
    private static Text power;
    private static Text income;
    private static Text expense;
    private static ProgressBar faithBar;
    private static Text builder;

    private static ImageView plagueStatus;
    private static ImageView droughtStatus;

    public IngameStage(){
        this.setPadding(new Insets(35));

        playArea = createPlayArea();
        displayArea = createDisplayArea();

        playArea.setAlignment(Pos.CENTER_LEFT);
        displayArea.setAlignment(Pos.CENTER);

        displayArea.setPrefWidth(525);

        this.setCenter(playArea);
        this.setRight(displayArea);
    }

    public static void update(int i, int j, String url){
        TileButton tmp = map.get(i * 5 + j);
        tmp.updateImage(url);
    }
    private GridPane createPlayArea(){
        map = new ArrayList<>();
        GridPane pane = new GridPane();
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                TileButton tmp = new TileButton(i, j);
                map.add(tmp);
                pane.add(tmp, i, j);
            }
        }


        return pane;
    }

    private VBox createDisplayArea(){
        VBox pane = new VBox();
        pane.setSpacing(30);
        pane.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));

        int s = 30;
        int h = 70;
        int w = 70;

        HBox dayRow = new HBox();
        dayRow.setPadding(new Insets(50));
        dayRow.setAlignment(Pos.CENTER);
        Text dayText = new Text("Day ");
        day = new Text("1");
        dayText.setFont(Font.font(h));
        day.setFont(Font.font(h));
        FlowPane statusBar = new FlowPane();
        dayRow.getChildren().addAll(dayText,day,statusBar);
        statusBar.setAlignment(Pos.CENTER);
        try{
            Image plague = new Image(ClassLoader.getSystemResourceAsStream("EventImage/PlagueEvent.png"));
            Image drought = new Image(ClassLoader.getSystemResourceAsStream("EventImage/DroughtEvent.png"));
            plagueStatus = new ImageView(plague);
            droughtStatus = new ImageView(drought);
            plagueStatus.setFitWidth(w);
            plagueStatus.setFitHeight(w);
            droughtStatus.setFitHeight(w);
            droughtStatus.setFitWidth(w);
            plagueStatus.setVisible(false);
            droughtStatus.setVisible(false);
            statusBar.getChildren().addAll(plagueStatus, droughtStatus);
        }
        catch(Exception e){

        }



        GridPane infoPane = new GridPane();
        infoPane.setAlignment(Pos.TOP_CENTER);
        infoPane.setHgap(15);
        infoPane.setVgap(15);
        Text moneyText = new Text("Money");
        money = new Text(": 1000");
        moneyText.setFont(Font.font(s));
        money.setFont(Font.font(s));
        infoPane.add(moneyText, 0, 0);
        infoPane.add(money, 1, 0);


        Text powerText = new Text("Power");
        power = new Text(": 0");
        powerText.setFont(Font.font(s));
        power.setFont(Font.font(s));
        infoPane.add(powerText, 0, 1);
        infoPane.add(power, 1, 1);

        Text incomeText = new Text("Income");
        income = new Text(": 0");
        incomeText.setFont(Font.font(s));
        income.setFont(Font.font(s));
        infoPane.add(incomeText, 0, 2);
        infoPane.add(income, 1, 2);

        Text expenseText = new Text("Expense");
        expense = new Text(": 50");
        expenseText.setFont(Font.font(s));
        expense.setFont(Font.font(s));
        infoPane.add(expenseText, 0, 3);
        infoPane.add(expense, 1, 3);

        Text builderText = new Text("Builder");
        builder = new Text(": 1/1");
        builderText.setFont(Font.font(s));
        builder.setFont(Font.font(s));
        infoPane.add(builderText, 0, 4);
        infoPane.add(builder, 1, 4);

        /*
        Text faithText = new Text("Faith");
        faith = new Text(": 0%");
        faithText.setFont(Font.font(s));
        faith.setFont(Font.font(s));
        infoPane.add(faithText, 0, 5);
        infoPane.add(faith, 1, 5);
         */
        Text faithText = new Text("Faith");
        faithText.setFont(Font.font(s));
        faithText.setTextAlignment(TextAlignment.CENTER);
        faithBar = new ProgressBar();
        faithBar.setProgress(0);
        faithBar.setPrefWidth(300);

        VBox faithRow = new VBox();
        faithRow.setAlignment(Pos.CENTER);
        faithRow.getChildren().addAll(faithText,faithBar);

        Button endBtn = new Button("END");
        endBtn.setAlignment(Pos.BOTTOM_RIGHT);
        endBtn.setTextAlignment(TextAlignment.CENTER);
        endBtn.setPrefSize(50,30);
        endBtn.setFont(Font.font(15));

        pane.getChildren().addAll(dayRow, infoPane,faithRow, endBtn);

        endBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                GameLoop.nextDay();
            }
        });
        pane.setAlignment(Pos.TOP_CENTER);
        return pane;
    }

    public static void updateInfo(){
        day.setText(": " + GameLoop.getDay());
        money.setText(": " + GameLogic.getInstance().getPlayer().getMoney() + "");
        power.setText(": " + GameLogic.getInstance().getPlayer().getPower() + "");
        income.setText(": " + (GameLogic.getInstance().getIncome() * GameLogic.getInstance().getIncomeMultiplier()) + "");
        expense.setText(": " + GameLogic.getInstance().getExpense() + "");
        builder.setText(": " + GameLogic.getInstance().getPlayer().getAvailableBuilder() + "/" + GameLogic.getInstance().getPlayer().getMaxBuilder());
        //faith.setText(": " + GameLogic.getInstance().getPlayer().getFaithPercentage() + "%");
        faithBar.setProgress((double)GameLogic.getInstance().getPlayer().getFaithPercentage() / 100);
    }

    public static void setDroughtStatus(boolean s){
        droughtStatus.setVisible(s);
    }
    public static void setPlagueStatus(boolean s){
        plagueStatus.setVisible(s);
    }
}
