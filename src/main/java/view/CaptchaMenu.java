package view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Random;

public class CaptchaMenu extends Application {
    ImageView imageView=new ImageView();
    String pass;
    private boolean CanPass=false;
    public static String[] images={"1181","1381","1491","1722","1959","2163","2177","2723","2785","3541","3847","3855"
            ,"3876","3967","4185","4310","4487","4578","4602","4681","4924","5326","5463"};
    @Override
    public void start(Stage stage) throws Exception {
        imageView.setImage(setImage());
        VBox pane=new VBox();
        Button button=new Button();
        button.setText("submit");
        TextField textField=new TextField();
        textField.setMaxWidth(200);
        pane.setAlignment(Pos.CENTER);
        pane.setSpacing(20);
        pane.setMinWidth(400);
        pane.setMinHeight(300);
        pane.getChildren().addAll(imageView,textField,button);
        Scene scene=new Scene(pane);
        stage.setScene(scene);
        stage.show();
        button.setOnMouseClicked(mouseEvent -> {
            System.out.println(pass);
            if(textField.getText().equals(pass)){
                System.out.println("pass");
                CanPass=true;
                stage.close();

            }
            else{
               imageView.setImage(setImage());
                System.out.println("error");
                textField.setText("");
            }
        });
    }
    public String randomAddress(){
        Random random=new Random();
        int imageInex=random.nextInt(23);
        String imgeName=images[imageInex]+".png";
        pass=images[imageInex];
        return imgeName;
    }
    public Image setImage(){
        return new Image(CaptchaMenu.class.getResource("/images/Captcha/"+randomAddress()).toString());
    }

    public boolean getCanPass() {
        return CanPass;
    }

    public void setCanPass(boolean canPass) {
        CanPass = canPass;
    }
}
