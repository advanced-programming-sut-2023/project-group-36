package project.controller;

import project.model.*;

import java.util.regex.Matcher;

public class TradeMenuController {
    public static String tradeRequest(Matcher matcher){
        String username = matcher.group("username");
        String resourceType = matcher.group("resourceType");
        int resourceAmount = Integer.parseInt(matcher.group("resourceAmount"));
        int price = Integer.parseInt(matcher.group("price"));
        String message = matcher.group("message");

        if (ApplicationManager.getUserByUsername(username)==null){
            return "Error: User not found!";
        }

        if (ApplicationManager.getCurrentGame().getCurrentGovernment().getResources().validResource(resourceType)){
            return "Error: Invalid resource type!";
        }
        Government requester = ApplicationManager.getCurrentGame().getGovernmentByUser(ApplicationManager.getCurrentUser());
        Government requested = ApplicationManager.getCurrentGame().getGovernmentByUser(ApplicationManager.getUserByUsername(username));
        Trade trade = new Trade(requester,requested,resourceType,price,resourceAmount,message);
        if(trade.check()!=null){
            return trade.check();
        }
        TradeMessage tradeMessage = new TradeMessage(message, requester, requested, trade);
        requested.addTradeMessage(tradeMessage);
        requester.addTrade(trade);
        requested.addTrade(trade);
        System.out.println(requested.getOwner().getUsername());
        return "Your business request has been successfully sent to the "+username;
    }

    public static String tradeList(){
        Government government = ApplicationManager.getCurrentGame().getCurrentGovernment();
        for (int i = 0; i < government.getTradeMessages().size(); i++) {
            TradeMessage tradeMessage = government.getTradeMessages().get(i);
            tradeMessage.show(i+1);
        }
        return "All your previous messages have been shown.";
    }

    public  static void tradeShowNotifications(){
        Government government = ApplicationManager.getCurrentGame().getCurrentGovernment();
        System.out.println("Your new notifications:");
        for (int i = 0; i < government.getTradeMessages().size(); i++) {
            TradeMessage tradeMessage = government.getTradeMessages().get(i);
            if (!tradeMessage.showCondition()){
                tradeMessage.show(i+1);
            }
        }
    }

    public static String tradeAccept(Matcher matcher){
        Government government = ApplicationManager.getCurrentGame().getCurrentGovernment();
        int id = Integer.parseInt(matcher.group("id"));
        String message = matcher.group("message");
        if (id>government.getTradeMessages().size()){
            return "Error: The id is out of range!";
        }
        TradeMessage tradeMessage = government.getTradeMessages().get(id-1);
        Trade trade = tradeMessage.getTrade();
        if (trade.check()!=null){
            return trade.check();
        }
        tradeMessage.accept();
        return "Trade successfully completed!";
    }

    public static String tradeHistory(){
        Government government = ApplicationManager.getCurrentGame().getCurrentGovernment();
        Trade trade;
        System.out.println("Your trade history:");
        for (int i = 0; i < government.getTrades().size(); i++) {
            trade = government.getTrades().get(i);
            trade.show(i+1);
        }
        if (government.getTrades().size()==0){
            return "There is nothing to display.";
        }
        return "The history of all your trades is shown.";
    }

}
