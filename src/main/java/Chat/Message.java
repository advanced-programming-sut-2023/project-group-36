package Chat;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import model.User;

public class Message {
    private User sender;
    private String content;
    private Conversation conversation;
    private String status;
    private boolean deletedForOwner;


    public String getContent() {
        return content;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Message(User sender, String content, Conversation conversation) {
        this.sender = sender;
        this.content = content;
        this.conversation = conversation;
        this.status="sending";
        deletedForOwner=false;
    }
    public Pane showContent(){
        Pane h=new Pane();
        h.getChildren().add(new Label(sender.getUsername()+"\n"+content));
        Label seen=new Label(status);
        seen.setLayoutX(0);
        seen.setLayoutY(195);
        h.getChildren().add(seen);
        h.setMaxWidth(200);

        return  h;
    }
}
