package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Random;

public class CaptchaMenu extends Application {
    public static String[] images={"1181","1381","1491","1722","1959","2163","2177","2723","2785","3541","3847","3855"
            ,"3876","3967","4185","4310","4487","4578","4602","4681","4924","5326","5463"};
    @Override
    public void start(Stage stage) throws Exception {
        Random random=new Random();
        int imageInex=random.nextInt(23);
        String imgeName=images[imageInex]+".png";
        ImageView imageView=new ImageView(new Image(CaptchaMenu.class.getResource("/images/Captcha/"+imgeName).toString()));
        Pane pane=new Pane();
        pane.getChildren().add(imageView);
        Scene scene=new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
}
