package Chat;

import model.User;

import java.util.ArrayList;

public class ChatMenu {
    String name;
    private ArrayList<User> users;
    private ArrayList<Message> messages;

    public ChatMenu(String name, ArrayList<User> users) {
        this.name = name;
        this.users = users;
        messages=new ArrayList<>();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

}
