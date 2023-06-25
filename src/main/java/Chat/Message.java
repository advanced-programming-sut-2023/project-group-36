package Chat;

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
}
