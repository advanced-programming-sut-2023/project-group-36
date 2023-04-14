import model.ApplicationManager;
import view.LoginMenu;

public class Main {
    public static void main(String[] args) {
        LoginMenu.run();
        ApplicationManager.exit();
    }
}