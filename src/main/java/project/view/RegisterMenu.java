package project.view;
import project.controller.Commands;
import project.controller.RegisterMenuController;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.regex.Matcher;

public class RegisterMenu {
    private final static Scanner scanner = Menu.getScanner();

    public static void run() throws NoSuchAlgorithmException {
        System.out.println("**<< Register Menu >>**");
        String input;
        String result;
        boolean inThisMenu = true;
        while (inThisMenu) {
            input = scanner.nextLine();
            if (input.matches(Commands.REGISTER.getRegex())) {
                Matcher matcher = Menu.getMatcher(input,Commands.REGISTER.getRegex());
                result = RegisterMenuController.register(matcher);
                System.out.println(result);
            }
            else if (input.equals("login menu")){
                inThisMenu = false;
                MainMenu.run();
            }
            else {
                System.out.println("Invalid command!");
            }
    }
}
}
