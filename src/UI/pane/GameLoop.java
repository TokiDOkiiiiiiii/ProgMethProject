package UI.pane;

import UI.alert.EventAlertBox;
import UI.alert.GameEndAlert;
import event.base.Event;
import event.main.Mercenary;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.utils.EventMaker;
import logic.utils.GameLogic;

public class GameLoop {

    private static int day;
    private static GameLogic gameInstance;
    private static EventMaker eventM;
    public static Stage st;


    public static void startGame(Stage stage) {
        // Initialize game
        st = stage;
        IngameStage inGame = new IngameStage();
        Scene inGameScene = new Scene(inGame, 1280, 720);
        stage.setFullScreen(false);
        stage.setResizable(false);
        stage.setScene(inGameScene);

        gameStart();

    }

    public static void gameStart(){
        GameLogic.newInstance();
        eventM = new EventMaker();
        //scanner = new Scanner(System.in);
        gameInstance = GameLogic.getInstance();
        day = 1;
    }
    public static void nextDay(){
        //System.out.println("Day " + (day + 1));
        day += 1;
        if (day == 100){
            GameEndAlert.display("The Savior", "You survived 100 days!");
            return;
        }
        event();
        gameInstance.dailyUpdate();
    }

    public static void event(){
        mainEvent();
        faithEvent();
        raidEvent();
    }

    public static void raidEvent(){
        if (day % 10 == 0){
            Event event = eventM.getRaidEvent();
            //GameLogic.getInstance().getPlayer().subMoney(200);
            EventAlertBox.display(event.toString(),event.getResult(),event.getUrl());
            if (GameLogic.getInstance().isGameOver()){
                GameEndAlert.display("The Loser", "Your army cannot fight");
            }
        }
    }
    public static void faithEvent(){
        Event event = eventM.getFaithEvent();
        if (event != null){
            EventAlertBox.display(event.toString(),event.getResult(),event.getUrl());
        }
    }

    public static void mainEvent(){
        Event event = eventM.getMainEvent(day);
        if (event !=  null && !(event instanceof Mercenary)){
            EventAlertBox.display(event.toString(), event.getResult(), event.getUrl());
        }
    }

    public static int getDay(){
        return day;
    }





}
