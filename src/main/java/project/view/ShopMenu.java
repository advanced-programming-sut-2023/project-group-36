package project.view;

import project.controller.Commands;
import project.controller.ShopMenuController;
import project.model.ApplicationManager;
import project.model.Tools;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.regex.Matcher;

public class ShopMenu {
    private final static Scanner scanner = Menu.getScanner();

    public static void run() throws NoSuchAlgorithmException, InterruptedException {
        String command;
        boolean isThisMenu = true;

        while (isThisMenu) {
            command = scanner.nextLine();
            if (Tools.inputCheckFormat(command)!=null){
                command = Tools.inputCheckFormat(command);
            }

            Matcher matcher;

            matcher = Menu.getMatcher(command, Commands.SHOW_PRICE_LIST.getRegex());
            if (matcher != null) {
                System.out.println(ShopMenuController.showPriceList()); //
                continue;
            }

            matcher = Menu.getMatcher(command, Commands.BUY.getRegex());
            if (matcher != null) {
                System.out.println(ShopMenuController.buyAndSell(matcher, "buy"));
                continue;
            }

            matcher = Menu.getMatcher(command, Commands.SELL.getRegex());
            if (matcher != null) {
                System.out.println(ShopMenuController.buyAndSell(matcher, "sell"));
                continue;
            }

            matcher = Menu.getMatcher(command, Commands.EXIT.getRegex());
            if (matcher != null) {
                isThisMenu = false;
                GameMenu.run(ApplicationManager.getCurrentGame());
            }

            matcher = Menu.getMatcher(command, Commands.QUIT_GAME.getRegex());
            if (matcher != null) {
                ApplicationManager.exit();
            }

            System.out.println("Invalid command!");
        }
    }

}
