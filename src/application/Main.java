package application;

import UI.misc.BackgroundMusic;
import UI.pane.StartGame;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    public static Scene startScene;
    @Override
    public void start(Stage stage) throws Exception {
        BackgroundMusic audio = new BackgroundMusic();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                audio.play();
            }
        });
        thread.run();

        StartGame startPane = new StartGame(stage);
        Scene scene  = new Scene(startPane, 1280 ,720);
        startScene = scene;
        stage.setTitle("Game Sang Mueang");
        stage.setFullScreen(false);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
