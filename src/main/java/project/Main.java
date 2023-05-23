package project;
import project.model.ApplicationManager;
import project.view.LoginMenu;
import java.security.NoSuchAlgorithmException;


public class Main {
    public static void main(String[] args) throws InterruptedException, NoSuchAlgorithmException {
        ApplicationManager.start();
        LoginMenu.run();
        ApplicationManager.exit();
    }
}