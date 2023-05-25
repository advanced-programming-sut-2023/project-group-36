package project.controller;

import project.model.ApplicationManager;
import project.model.Government;
import java.util.regex.Matcher;

public class ShopMenuController {

    public static String showPriceList() {
        String result;

        result = "Foods:" + "\n" +
                 "Bread = 40\n"      +
                 "Rice = 160\n"      +
                 "Apple = 40\n"      +
                 "Meat = 40\n";

        result += "Primary sources:" + "\n" +
                "Stone = 20\n"                    +
                "wood = 70\n"                    +
                "iron = 225\n";

        result += "Weapon:" + "\n" +
                "blacksmith 100\n"     +
                "Fletcher 100\n"       +
                "Poleturner 100\n"     +
                "Oil 100";

        return result;
    }

    public static String buyAndSell(Matcher matcher, String function) {
        String name = matcher.group("itemName");
        int amount = Integer.parseInt(matcher.group("itemAmount"));

        if (amount <= 0) {
            return "Invalid amount!";
        }

        Government government = ApplicationManager.getCurrentGame().getCurrentGovernment();

        if (function.equals("buy"))
            return buy(name, amount, 0, government, 0);
        else
            return sell(name, amount, 0, government, 0);
    }

    public static String buy(String name, int amount, int sumOfPrice, Government government, int index) {

        switch (name) {
            //Foods
            case "Bread":
                sumOfPrice = -40 * amount;
                index = -1;
                break;
            case "Rice":
                sumOfPrice = -160 * amount;
                index = -2;
                break;
            case "Apple":
                sumOfPrice = -40 * amount;
                index = -3;
                break;
            case "Meat":
                sumOfPrice = -40 * amount;
                index = -4;
                break;

            // Primary resources
            case "Stone":
                sumOfPrice = -20 * amount;
                index = 1;
                break;
            case "Wood":
                sumOfPrice = -70 * amount;
                index = 2;
                break;
            case "Iron":
                sumOfPrice = -225 * amount;
                index = 3;
                break;

            // Weapons
            case "Oil":
                sumOfPrice = -100 * amount;
                index = 7;
                break;
        }

        if (index == 0)
            return "Invalid item's name!";

        assert government != null;
        if (sumOfPrice > government.getCoins())
            return "Your coins isn't enough";

        changes(amount, sumOfPrice, government, name);
        return "You bought " + amount + " " + name;
    }

    public static String sell(String name, int amount, int sumOfPrice, Government government, int index) {

        switch (name) {
            //Foods
            case "bread":
                sumOfPrice = 40 * amount;
                index = -1;
                break;
            case "rice":
                sumOfPrice = 160 * amount;
                index = -2;
                break;
            case "apple":
                sumOfPrice = 40 * amount;
                index = -3;
                break;
            case "meat":
                sumOfPrice = 40 * amount;
                index = -4;
                break;

            // Primary resources
            case "Stone":
                sumOfPrice = 20 * amount;
                index = 1;
                break;
            case "Wood":
                sumOfPrice = 70 * amount;
                index = 2;
                break;
            case "Iron":
                sumOfPrice = 225 * amount;
                index = 3;
                break;

            // Weapons
            case "Oil":
                sumOfPrice = 100 * amount;
                index = 7;
                break;
        }

        if (index == 0)
            return "Invalid item's name!";

        changes(amount, sumOfPrice, government, name);
        return "You sell " + amount + " " + name;
    }

    private static void changes(int amount, int sumOfPrice, Government government, String type) {
        if (type.equals("bread") || type.equals("meat") || type.equals("apple") || type.equals("rice")){
            government.changeAmountOfFoods(type, amount);
        }
        else{
            government.changeAmountOfResource(type,amount);
        }
        government.changeCoins(sumOfPrice);
    }
}