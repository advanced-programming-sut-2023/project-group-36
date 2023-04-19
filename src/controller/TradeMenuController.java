package controller;

import model.*;

import java.util.regex.Matcher;

public class TradeMenuController {
    private static int getResourceNumber(String type){
        switch (type) {
            case "x":
                return 1;
            case "y":
                return 2;
            case "Z":
                return 3;
            case "W":
                return 4;
            case "U":
                return 5;
        }
        return -1;
    }
    public static String tradeRequest(Matcher matcher){
        String username = matcher.group("username");
        String resourceType = matcher.group("resourceType");
        int resourceAmount = Integer.parseInt(matcher.group("resourceAmount"));
        int price = Integer.parseInt(matcher.group("price"));
        String message = matcher.group("message");
        if (ApplicationManager.getUserByUsername(username)==null){
            return "Error: User not found!";
        }
        int resourceTypeNumber = getResourceNumber(resourceType);
        if (resourceTypeNumber==-1){
            return "Error: Invalid resource type!";
        }
        Government sender = ApplicationManager.getCurrentGame().getGovernmentByUser(ApplicationManager.getCurrentUser());
        Government receiver = ApplicationManager.getCurrentGame().getGovernmentByUser(ApplicationManager.getUserByUsername(username));
        Trade trade = new Trade(sender,receiver,resourceTypeNumber,price,resourceAmount,message);
        sender.addTrade(trade);
        receiver.addTrade(trade);
        return "Your business request has been successfully sent to the "+username;
    }

    public static String tradeList(){
        Government government = ApplicationManager.getCurrentGame().getCurrentGovernment();
        for (int i = 0; i < government.getTradeMessages().size(); i++) {
            TradeMessage tradeMessage = government.getTradeMessages().get(i);
            tradeMessage.show(i+1);
        }
        government.delShowedMessages();
        return "...";
    }

    public static String tradeAccept(Matcher matcher){
        Government government = ApplicationManager.getCurrentGame().getCurrentGovernment();
        int id = Integer.parseInt(matcher.group("id"));
        String message = matcher.group("message");
        if (id>government.getTradeMessages().size()){
            return "Error: The id is out of range!";
        }
        TradeMessage tradeMessage = government.getTradeMessages().get(id-1);
        tradeMessage.accept();
        return "Trade successfully completed!";
    }

    public static String tradeHistory(){
        return "...";
    }

}
