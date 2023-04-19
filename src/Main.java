import controller.RegisterMenuController;
import model.ApplicationManager;
import view.LoginMenu;
import view.RegisterMenu;

import java.util.regex.Matcher;

public class Main {
    public static void main(String[] args) {
        //RegisterMenu.run();
        LoginMenu.run();
        ApplicationManager.exit();
    }
}