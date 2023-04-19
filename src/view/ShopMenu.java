package view;

import controller.Commands;
import controller.ShopMenuController;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ShopMenu {
    private final static Scanner scanner = Menu.getScanner();
    private final ShopMenuController shopMenuController;

    public ShopMenu(ShopMenuController shopMenuController) {
        this.shopMenuController = shopMenuController;
    }

    public static void run(){

        while (true) {
            String command = scanner.nextLine();
            Matcher matcher;

            matcher = Menu.getMatcher(command, String.valueOf(Commands.SHOW_PRICE_LIST));
            if (matcher != null) {
                System.out.println(shopMenuController.showPriceList()); //
                continue;
            }

            matcher = Menu.getMatcher(command, String.valueOf(Commands.BUY));
            if (matcher != null) {
                System.out.println(shopMenuController.buy(matcher));
                continue;
            }

            matcher = Menu.getMatcher(command, String.valueOf(Commands.SELL));
            if (matcher != null) {
                System.out.println(shopMenuController.sell(matcher));
                continue;
            }

            matcher = Menu.getMatcher(command, String.valueOf(Commands.EXIT));
            if (matcher != null)
                break;

            System.out.println("Invalid command!");
        }
    }

}
