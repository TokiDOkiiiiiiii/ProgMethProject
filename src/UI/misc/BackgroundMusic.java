package UI.misc;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;

public class BackgroundMusic {
    private static MediaPlayer mediaPlayer;

    public void play(){
        try {
            String path = "T:/ProjectProgMeth/ProjectBeforeJavaFX/src/res/ThemeSong.wav";
            //String path = ClassLoader.getSystemResource("ThemeSong.wav").toString();
            //System.out.println(path);


            Media media = new Media(new File(path).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.setVolume(0.3);
            mediaPlayer.play();
        }
        catch(Exception e){
            //System.out.println("Nor found");
        }
    }
}
