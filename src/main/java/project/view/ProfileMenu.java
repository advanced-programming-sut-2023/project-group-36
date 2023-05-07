package project.view;

import project.controller.Commands;
import project.controller.ProfileMenuController;
import project.model.Tools;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.regex.Matcher;

public class ProfileMenu {
    private final static Scanner scanner = Menu.getScanner();

    public static void run() throws NoSuchAlgorithmException {
        System.out.println("**<< Profile Menu >>**");
        boolean inThisMenu = true;
        String formattedInput;

        while (true) {
            String command = scanner.nextLine();
            if (Tools.inputCheckFormat(command)!=null){
                command = Tools.inputCheckFormat(command);
            }
            Matcher matcher;

            matcher = Menu.getMatcher(command, String.valueOf(Commands.CHANGE_USERNAME));
            if (matcher != null) {
                System.out.println("change username");
                System.out.println(ProfileMenuController.changeUsername(matcher));
                continue;
            }

            matcher = Menu.getMatcher(command, String.valueOf(Commands.CHANGE_NICKNAME));
            if (matcher != null) {
                System.out.println(ProfileMenuController.changeNickname(matcher));
                continue;
            }

            matcher = Menu.getMatcher(command, String.valueOf(Commands.CHANGE_PASSWORD));
            if (matcher != null) {
                String result = ProfileMenuController.changePassword_1(matcher);
                System.out.println(result);
                if (result.equals("Please enter your new password again")) {
                    String newPassword = matcher.group("new-password");
                    command = scanner.nextLine();
                    matcher = Menu.getMatcher(command, "(?<new-password>[^\n]+)");
                    assert matcher != null;
                    System.out.println(ProfileMenuController.changePassword_2(matcher, newPassword));
                }
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
                System.out.println(ProfileMenuController.removeSlogan());
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
            if (matcher != null){
                break;
            }

            System.out.println("Invalid command!");
        }
    }

}
