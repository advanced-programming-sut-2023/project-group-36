package project.view;

import project.controller.Commands;
import project.controller.ShopMenuController;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ShopMenu {
    private final static Scanner scanner = Menu.getScanner();

    public static void run(){

        while (true) {
            String command = scanner.nextLine();
            Matcher matcher;

            matcher = Menu.getMatcher(command, String.valueOf(Commands.SHOW_PRICE_LIST));
            if (matcher != null) {
                System.out.println(ShopMenuController.showPriceList()); //
                continue;
            }

            matcher = Menu.getMatcher(command, String.valueOf(Commands.BUY));
            if (matcher != null) {
                System.out.println(ShopMenuController.buyAndSell(matcher, "buy"));
                continue;
            }

            matcher = Menu.getMatcher(command, String.valueOf(Commands.SELL));
            if (matcher != null) {
                System.out.println(ShopMenuController.buyAndSell(matcher, "sell"));
                continue;
            }

            matcher = Menu.getMatcher(command, String.valueOf(Commands.EXIT));
            if (matcher != null)
                break;

            System.out.println("Invalid command!");
        }
    }

}
