package project.view;

import project.controller.Commands;
import project.controller.RegisterMenuController;
import project.controller.TradeMenuController;

import java.util.Scanner;
import java.util.regex.Matcher;

public class TradeMenu {
    private final static Scanner scanner = Menu.getScanner();


    public static void run(){
        System.out.println("**<< Trade Menu >>**");
        TradeMenuController.tradeShowNotifications();
        String input;
        String result;
        boolean inThisMenu = true;
        while (inThisMenu) {
            input = scanner.nextLine();
            if (input.matches(Commands.TRADE_REQUEST.getRegex())) {
                Matcher matcher = Menu.getMatcher(input,Commands.TRADE_REQUEST.getRegex());
                result = TradeMenuController.tradeRequest(matcher);
                System.out.println(result);
            }
            else if (input.matches(Commands.TRADE_ACCEPT.getRegex())) {
                Matcher matcher = Menu.getMatcher(input,Commands.TRADE_ACCEPT.getRegex());
                result = TradeMenuController.tradeAccept(matcher);
                System.out.println(result);
            }
            else if (input.matches(Commands.TRADE_LIST.getRegex())) {
                Matcher matcher = Menu.getMatcher(input,Commands.TRADE_LIST.getRegex());
                result = TradeMenuController.tradeList();
                System.out.println(result);
            }
            else if (input.matches(Commands.TRADE_HISTORY.getRegex())) {
                Matcher matcher = Menu.getMatcher(input,Commands.TRADE_HISTORY.getRegex());
                result = TradeMenuController.tradeHistory();
                System.out.println(result);
            }
            else if (input.equals("back")){
                inThisMenu = false;
                GameMenu.run();
            }
            else {
                System.out.println("Invalid command!");
            }
        }
    }
}
