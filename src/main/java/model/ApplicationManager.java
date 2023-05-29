package model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class ApplicationManager {
    private Game currentGame;
    static MediaPlayer mediaPlayer=new MediaPlayer(new Media(ApplicationManager.class.getResource("/mokhtarnameh-barkhiz.mp3").toString()));
    public static void run(){
        mediaPlayer.setVolume(50);
        mediaPlayer.play();
    }

    public static Game getCurrentGame() {
        return getCurrentGame();
    }
}
