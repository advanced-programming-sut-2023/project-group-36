package project;
import project.model.ApplicationManager;
import project.view.LoginMenu;
import project.view.RegisterMenu;



public class Main {
    public static void main(String[] args) {
        RegisterMenu.run();
        LoginMenu.run();
        ApplicationManager.exit();
    }
}