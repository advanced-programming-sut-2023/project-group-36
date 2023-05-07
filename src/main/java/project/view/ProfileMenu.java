package project.view;

import project.controller.Commands;
import project.controller.ProfileMenuController;

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
            Matcher matcher;
            Matcher matcher1;

            matcher = Menu.getMatcher(command, Commands.CHANGE_USERNAME.getRegex());
            if (matcher != null) {
                System.out.println(ProfileMenuController.changeUsername(matcher));
                continue;
            }

            matcher = Menu.getMatcher(command, Commands.CHANGE_NICKNAME.getRegex());
            if (matcher != null) {
                System.out.println(ProfileMenuController.changeNickname(matcher));
                continue;
            }

            matcher = Menu.getMatcher(command, Commands.CHANGE_PASSWORD.getRegex());
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

            matcher = Menu.getMatcher(command, Commands.CHANGE_EMAIL.getRegex());
            if (matcher != null) {
                matcher1 = Menu.getMatcher(matcher.group("email"), String.valueOf(Commands.EMAIL));
                assert matcher1 != null;
                System.out.println(ProfileMenuController.changeEmail(matcher, matcher1));
                continue;
            }

            matcher = Menu.getMatcher(command, Commands.CHANGE_SLOGAN.getRegex());
            if (matcher != null) {
                System.out.println(ProfileMenuController.changeSlogan(matcher));
                continue;
            }

            matcher = Menu.getMatcher(command, Commands.REMOVE_SLOGAN.getRegex());
            if (matcher != null) {
                System.out.println(ProfileMenuController.removeSlogan());
                continue;
            }

            matcher = Menu.getMatcher(command, Commands.DISPLAY_HIGH_SCORE.getRegex());
            if (matcher != null) {
                System.out.println(ProfileMenuController.displayHighScore());
                continue;
            }

            matcher = Menu.getMatcher(command, Commands.DISPLAY_RANK.getRegex());
            if (matcher != null) {
                System.out.println(ProfileMenuController.displayRank());
                continue;
            }

            matcher = Menu.getMatcher(command, Commands.DISPLAY_SLOGAN.getRegex());
            if (matcher != null) {
                System.out.println(ProfileMenuController.displaySlogan());
                continue;
            }

            matcher = Menu.getMatcher(command, Commands.DISPLAY.getRegex());
            if (matcher != null) {
                System.out.println(ProfileMenuController.display());
                continue;
            }

            matcher = Menu.getMatcher(command, Commands.EXIT.getRegex());
            if (matcher != null){
                break;
            }

            System.out.println("Invalid command!");
        }
    }

}
