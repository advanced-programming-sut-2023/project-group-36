package controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class ApplicationManger {
    static MediaPlayer mediaPlayer=new MediaPlayer(new Media(ApplicationManger.class.getResource("/mokhtarnameh-barkhiz.mp3").toString()));
    public static void run(){
        mediaPlayer.setVolume(50);
        mediaPlayer.play();
    }
}
