package project.controller;

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
                "rock = 20\n"                   +
                "wood = 70\n"                   +
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

    public static String buy(Matcher matcher) {
        return "";
    }

    public static String sell(Matcher matcher) {
        return "";
    }
}
