package project.view;
import project.controller.Commands;
import project.view.Menu;
import main.java.project.controller.ProfileMenuController;
import java.util.Scanner;
import java.util.regex.Matcher;

public class ProfileMenu {
    private final static Scanner scanner = Menu.getScanner();

    public static void run(){
        while (true) {
            String command = scanner.nextLine();
            Matcher matcher;

            matcher = Menu.getMatcher(command, String.valueOf(Commands.CHANGE_USERNAME));
            if (matcher != null) {
                System.out.println(ProfileMenuController.changeUsername(matcher)); //
                continue;
            }

            matcher = Menu.getMatcher(command, String.valueOf(Commands.CHANGE_NICKNAME));
            if (matcher != null) {
                System.out.println(ProfileMenuController.changeNickname(matcher));
                continue;
            }

            matcher = Menu.getMatcher(command, String.valueOf(Commands.CHANGE_PASSWORD));
            if (matcher != null) {
                System.out.println(ProfileMenuController.changePassword(matcher));
                continue;
            }

            matcher = Menu.getMatcher(command, String.valueOf(Commands.CHANGE_EMAIL));
            if (matcher != null) {
                System.out.println(ProfileMenuController.changeEmail(matcher));
                continue;
            }

            matcher = Menu.getMatcher(command, String.valueOf(Commands.CHANGE_SLOGAN));
            if (matcher != null) {
                System.out.println(ProfileMenuController.changeSlogan(matcher));
                continue;
            }

            matcher = Menu.getMatcher(command, String.valueOf(Commands.REMOVE_SLOGAN));
            if (matcher != null) {
                System.out.println(ProfileMenuController.removeSlogan(matcher));
                continue;
            }

            matcher = Menu.getMatcher(command, String.valueOf(Commands.DISPLAY_HIGH_SCORE));
            if (matcher != null) {
                System.out.println(ProfileMenuController.displayHighScore());
                continue;
            }

            matcher = Menu.getMatcher(command, String.valueOf(Commands.DISPLAY_RANK));
            if (matcher != null) {
                System.out.println(ProfileMenuController.displayRank());
                continue;
            }

            matcher = Menu.getMatcher(command, String.valueOf(Commands.DISPLAY_SLOGAN));
            if (matcher != null) {
                System.out.println(ProfileMenuController.displaySlogan());
                continue;
            }

            matcher = Menu.getMatcher(command, String.valueOf(Commands.DISPLAY));
            if (matcher != null) {
                System.out.println(ProfileMenuController.display());
                continue;
            }

            matcher = Menu.getMatcher(command, String.valueOf(project.controller.Commands.EXIT));
            if (matcher != null)
                break;

            System.out.println("Invalid command!");
        }
    }

}
