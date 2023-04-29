package project.controller;

import project.model.ApplicationManager;
import project.model.Government;
import java.util.regex.Matcher;

public class ShopMenuController {

    public static String showPriceList() {
        String result;

        result = "Foods:" + "\n" +
                 "bread = 40\n"      +
                 "rice = 160\n"      +
                 "apple = 40\n"      +
                 "meat = 40\n";

        result += "Primary sources:" + "\n" +
                "rock = 20\n"                    +
                "wood = 70\n"                    +
                "iron = 225\n";

        result += "Weapon:" + "\n" +
                "blacksmith 100\n"     +
                "Fletcher 100\n"       +
                "Poleturner 100\n"     +
                "oil smelter 100";

        result += "Urban structures" + "\n" +
                "Church = 250\n"                +
                "Cathedral 1000\n"              +
                "armourer = 100\n";

        result += "Types and components of castles" + "\n" +
                  "engineer guild = 100\n"                     +
                  "stable = 400\n";

        result += "Food processing:" + "\n" +
                  "Inn = 100";

        return result;
    }

    public static String buyAndSell(Matcher matcher, String function) {
        String name = matcher.group("item’s name");
        int amount = Integer.parseInt(matcher.group("item’s amount"));

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
            case "bread":
                sumOfPrice = -40 * amount;
                index = -1;
                break;
            case "rice":
                sumOfPrice = -160 * amount;
                index = -2;
                break;
            case "apple":
                sumOfPrice = -40 * amount;
                index = -3;
                break;
            case "meat":
                sumOfPrice = -40 * amount;
                index = -4;
                break;

            // Primary resources
            case "rock":
                sumOfPrice = -20 * amount;
                index = 1;
                break;
            case "wood":
                sumOfPrice = -70 * amount;
                index = 2;
                break;
            case "iron":
                sumOfPrice = -225 * amount;
                index = 3;
                break;
            // Weapons
            case "blacksmith":
                sumOfPrice = -100 * amount;
                index = 4;
                break;
            case "Fletcher":
                sumOfPrice = -100 * amount;
                index = 5;
                break;
            case "Poleturner":
                sumOfPrice = -100 * amount;
                index = 6;
                break;
            case "oil smelter":
                sumOfPrice = -100 * amount;
                index = 7;
                break;
            //Urban structures
            case "Church":
                sumOfPrice = -250 * amount;
                index = 8;
                break;
            case "Cathedral":
                sumOfPrice = -1000 * amount;
                index = 9;
                break;
            case "armourer":
                sumOfPrice = -100 * amount;
                index = 10;
                break;
            //Types and components of castles
            case "engineer guild":
                sumOfPrice = -100 * amount;
                index = 11;
                break;
            case "stable":
                sumOfPrice = -400 * amount;
                index = 12;
                break;
            //Food processing
            case "Inn":
                sumOfPrice = -100 * amount;
                index = 13;
                break;
        }

        if (index == 0)
            return "Invalid item's name";

        assert government != null;
        if (sumOfPrice > government.getCoins())
            return "Your coins isn't enough";

        changes(amount, sumOfPrice, government, index);
        return "You bought " + amount + " " + name;
    }

    public static String sell(String name, int amount, int sumOfPrice, Government government, int index) {

        switch (name) {
            case "bread":
                sumOfPrice = 24 * amount;
                index = -1;
                break;
            case "rice":
                sumOfPrice = 96 * amount;
                index = -2;
                break;
            case "apple":
                sumOfPrice = 24 * amount;
                index = -3;
                break;
            case "meat":
                sumOfPrice = 24 * amount;
                index = -4;
                break;

            // Primary resources
            case "rock":
                sumOfPrice = 12 * amount;
                index = 1;
                break;
            case "wood":
                sumOfPrice = 42 * amount;
                index = 2;
                break;
            case "iron":
                sumOfPrice = 105 * amount;
                index = 3;
                break;
            // Weapons
            case "blacksmith":
                sumOfPrice = 60 * amount;
                index = 4;
                break;
            case "Fletcher":
                sumOfPrice = 60 * amount;
                index = 5;
                break;
            case "Poleturner":
                sumOfPrice = 60 * amount;
                index = 6;
                break;
            case "oil smelter":
                sumOfPrice = 60 * amount;
                index = 7;
                break;
            //Urban structures
            case "Church":
                sumOfPrice = 150 * amount;
                index = 8;
                break;
            case "Cathedral":
                sumOfPrice = 600 * amount;
                index = 9;
                break;
            case "armourer":
                sumOfPrice = 60 * amount;
                index = 10;
                break;
            //Types and components of castles
            case "engineer guild":
                sumOfPrice = 60 * amount;
                index = 11;
                break;
            case "stable":
                sumOfPrice = 240 * amount;
                index = 12;
                break;
            //Food processing
            case "Inn":
                sumOfPrice = 60 * amount;
                index = 13;
                break;
        }

        if (index == 0)
            return "Invalid item's name";

        changes(amount, sumOfPrice, government, index);
        return "You sell " + amount + " " + name;
    }

    private static void changes(int amount, int sumOfPrice, Government government, int index) {
        String type;
        if (index < 0) {
            index *= -1;
            type = "type" + index;
            government.changeAmountOfFoods(type, amount);
        }
        else {
            type = "type" + index;
            government.changeAmountOfResource(type, amount);
        }

        government.changeCoins(sumOfPrice);
    }
}