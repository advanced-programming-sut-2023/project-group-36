package Chat;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import model.User;

public class Message {
    private User sender;
    private String content;
    private Conversation conversation;

    public Message(User sender, String content, Conversation conversation) {
        this.sender = sender;
        this.content = content;
        this.conversation = conversation;
    }
    public HBox showContent(){
        HBox h=new HBox();
        h.getChildren().add(new Label(sender.getUsername()+"\n"+content));
        h.setAlignment(Pos.TOP_LEFT);
        h.setMaxWidth(200);

        return  h;
    }
}
