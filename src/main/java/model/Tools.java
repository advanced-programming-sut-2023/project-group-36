package model;

import project.controller.CommandFormat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {



    public static String passwordWeakCheck(String password){
        if (password.length()<6){
            return "The password is weak: The length of the password must be greater than 6!";
        }
        if (!password.matches(".*[A-Z].*")){
            return "The password is weak: at least one capital letter is required!";
        }
        if (!password.matches(".*[a-z].*")){
            return "The password is weak: at least one small letter is required!";
        }
        if (!password.matches(".*[0-9].*")){
            return "The password is weak: at least one number is required!";
        }
        if (!password.matches(".*[#*\\-+&^%$@!.(){}].*")){
            return "The password is weak: at least one special character is required!";
        }
        return "Good";
    }


    private static ArrayList<String> splitString(String input) {
        ArrayList<String> parts = new ArrayList<>();
        Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(input);
        while (m.find()) {
            parts.add(m.group(1).replace("\"", "").trim());
        }
        return parts;
    }

    private static String removeString(String str1, String str2) {
        if (str2.startsWith(str1)) {
            return str2.substring(str1.length());
        } 
        else {
            return null;
        }
    }

    private static String commandFormatting(ArrayList<String> flags, ArrayList<String> parts, String start) {
        String result = start + " ";
        ArrayList<String> availableFlags = new ArrayList<>();
        for (String flag : flags) {
            for (String part : parts) {
                if (part.equals(flag)) {
                    availableFlags.add(part);
                    break;
                }
            }
        }
        for (int i = 0; i < availableFlags.size(); i++) {
            String flag = availableFlags.get(i);
            int index = parts.indexOf(flag);
            result += availableFlags.get(i) + " ";
            if (index != -1 && index < parts.size() - 1 && !availableFlags.contains(parts.get(index + 1))) {
                result+= parts.get(index + 1);
                if (i != availableFlags.size()-1){
                    result += " ";
                }
                parts.remove(index); // x --> Are ok
                parts.remove(index); // x+1 --> Are ok
            } 
            else {
                result+="";
            }
        }
        if (parts.size()!=0){
            result = start;
        }
        return result;
    }


    public static String inputFormatting(String input , CommandFormat commandFormat){
        String start = commandFormat.getStart();
        String[] flagsArray = commandFormat.getFlags();
        if (!input.matches(start+".+")){
            return null;
        }
        ArrayList<String> parts = splitString(removeString(start,input));
        ArrayList<String> flags = new ArrayList<>();
        Collections.addAll(flags, flagsArray);
        return commandFormatting(flags,parts,start);

    }


    public static String inputCheckFormat(String input) {
        String formattedInput;
        if ((formattedInput = Tools.inputFormatting(input, CommandFormat.REGISTER)) != null) {
            return formattedInput;
        }
        if ((formattedInput = Tools.inputFormatting(input, CommandFormat.QUESTION_ANSWER)) != null) {
            return formattedInput;
        }
        if ((formattedInput = Tools.inputFormatting(input, CommandFormat.LOGIN)) != null) {
            return formattedInput;
        }
        if ((formattedInput = Tools.inputFormatting(input, CommandFormat.LOGIN_LOGGED_IN)) != null) {
            return formattedInput;
        }
        if ((formattedInput = Tools.inputFormatting(input, CommandFormat.CHANGE_PASSWORD)) != null) {
            return formattedInput;
        }
        if ((formattedInput = Tools.inputFormatting(input, CommandFormat.DROP_ROCK)) != null) {
            return formattedInput;
        }
        if ((formattedInput = Tools.inputFormatting(input, CommandFormat.DROP_TREE)) != null) {
            return formattedInput;
        }
        if ((formattedInput = Tools.inputFormatting(input, CommandFormat.SET_TEXTURE_RECTANGLE)) != null) {
            return formattedInput;
        }
        if ((formattedInput = Tools.inputFormatting(input, CommandFormat.SET_TEXTURE)) != null) {
            return formattedInput;
        }
        if ((formattedInput = Tools.inputFormatting(input, CommandFormat.SET_GOVERNMENT)) != null) {
            return formattedInput;
        }
        if ((formattedInput = Tools.inputFormatting(input, CommandFormat.DROP_UNIT)) != null) {
            return formattedInput;
        }
        if ((formattedInput = Tools.inputFormatting(input, CommandFormat.DROP_BUILDING)) != null) {
            return formattedInput;
        }
        if ((formattedInput = Tools.inputFormatting(input, CommandFormat.SHOW_MAP)) != null) {
            return formattedInput;
        }
        if ((formattedInput = Tools.inputFormatting(input, CommandFormat.SHOW_DETAILS)) != null) {
            return formattedInput;
        }
        if ((formattedInput = Tools.inputFormatting(input, CommandFormat.SELECT_BUILDING)) != null) {
            return formattedInput;
        }
        if ((formattedInput = Tools.inputFormatting(input, CommandFormat.SELECT_UNIT)) != null) {
            return formattedInput;
        }
        if ((formattedInput = Tools.inputFormatting(input, CommandFormat.MOVE_UNIT)) != null) {
            return formattedInput;
        }
        if ((formattedInput = Tools.inputFormatting(input, CommandFormat.PATROL_UNIT)) != null) {
            return formattedInput;
        }
        if ((formattedInput = Tools.inputFormatting(input, CommandFormat.STOP_UNIT)) != null) {
            return formattedInput;
        }
        if ((formattedInput = Tools.inputFormatting(input, CommandFormat.ATTACK_ENEMY)) != null) {
            return formattedInput;
        }
        if ((formattedInput = Tools.inputFormatting(input, CommandFormat.ATTACK_LAUNCH)) != null) {
            return formattedInput;
        }
        if ((formattedInput = Tools.inputFormatting(input, CommandFormat.DIG_TUNNEL)) != null) {
            return formattedInput;
        }
        if ((formattedInput = Tools.inputFormatting(input, CommandFormat.CLEAR_BLOCK)) != null) {
            return formattedInput;
        }
        if ((formattedInput = Tools.inputFormatting(input, CommandFormat.BUY)) != null) {
            return formattedInput;
        }
        if ((formattedInput = Tools.inputFormatting(input, CommandFormat.SELL)) != null) {
            return formattedInput;
        }
        return null;
    }


    private static String[] print0_9AsciiArt(int number) {
        String[] asciiForm = new String[7];
        switch (number) {
            case 0:
                asciiForm[0]=getRandomString(8);
                asciiForm[1]="..####..";
                asciiForm[2]=".##..##.";
                asciiForm[3]=".##..##.";
                asciiForm[4]=".##..##.";
                asciiForm[5]="..####..";
                asciiForm[6]=getRandomString(8);
                break;
            case 1:
                asciiForm[0]=getRandomString(8);
                asciiForm[1]="...##...";
                asciiForm[2]="..###...";
                asciiForm[3]="...##...";
                asciiForm[4]="...##...";
                asciiForm[5]=".######.";
                asciiForm[6]=getRandomString(8);
                break;
            case 2:
                asciiForm[0]=getRandomString(8);
                asciiForm[1]=".######.";
                asciiForm[2]=".....##.";
                asciiForm[3]="..####..";
                asciiForm[4]=".##.....";
                asciiForm[5]=".######.";
                asciiForm[6]=getRandomString(8);
                break;
            case 3:
                asciiForm[0]=getRandomString(8);
                asciiForm[1]=".######.";
                asciiForm[2]="....##..";
                asciiForm[3]="...###..";
                asciiForm[4]=".....##.";
                asciiForm[5]=".#####..";
                asciiForm[6]=getRandomString(8);
                break;
            case 4:
                asciiForm[0]=getRandomString(8);
                asciiForm[1]=".....##.";
                asciiForm[2]=".##..##.";
                asciiForm[3]=".######.";
                asciiForm[4]=".....##.";
                asciiForm[5]=".....##.";
                asciiForm[6]=getRandomString(8);
                break;
            case 5:
                asciiForm[0]=getRandomString(8);
                asciiForm[1]=".######.";
                asciiForm[2]=".##.....";
                asciiForm[3]="..####..";
                asciiForm[4]=".....##.";
                asciiForm[5]="..#####..";
                asciiForm[6]=getRandomString(8);
                break;
            case 6:
                asciiForm[0]=getRandomString(8);
                asciiForm[1]="...##...";
                asciiForm[2]="..##....";
                asciiForm[3]=".#####..";
                asciiForm[4]=".##..##.";
                asciiForm[5]="..####..";
                asciiForm[6]=getRandomString(8);
                break;
            case 7:
                asciiForm[0]=getRandomString(8);
                asciiForm[1]=".######.";
                asciiForm[2]="....##..";
                asciiForm[3]="...##...";
                asciiForm[4]="..##....";
                asciiForm[5]=".##.....";
                asciiForm[6]=getRandomString(8);
                break;
            case 8:
                asciiForm[0]=getRandomString(8);
                asciiForm[1]="..####..";
                asciiForm[2]=".##..##.";
                asciiForm[3]="..####..";
                asciiForm[4]=".##..##.";
                asciiForm[5]="..####..";
                asciiForm[6]=getRandomString(8);
                break;
            case 9:
                asciiForm[0]=getRandomString(8);
                asciiForm[1]="..####..";
                asciiForm[2]=".##..##.";
                asciiForm[3]="..####..";
                asciiForm[4]="...##...";
                asciiForm[5]="..##....";
                asciiForm[6]=getRandomString(8);
                break;
        }
        return (asciiForm);
    }
    private static String getRandomChar() {
        Random random = new Random();
        String[] randomChars = {"^","#","$","+","&","*","?","!","%"};
        int randomInt = random.nextInt(9);
        return randomChars[randomInt];
    }
    private static String getRandomString(int count){
        String string = "";
        for (int i = 0; i < count; i++) {
            string+=getRandomChar();
        }
        return string;
    }
    private static ArrayList<Integer> getRandomNumbers(){
        Random random = new Random();
        ArrayList<Integer> numbers = new ArrayList<>();
        int count = random.nextInt(4) + 4;
        for (int i = 0; i < count; i++) {
            numbers.add(random.nextInt(9));
        }
        return numbers;
    }      
    private static void printCaptcha(ArrayList<Integer> nums){
        for (int i = 0; i < 7; i++) {
            for (Integer num : nums) {
                System.out.print(getRandomString(2));
                System.out.print(print0_9AsciiArt(num)[i]);
                System.out.print(getRandomString(2));
            }
            System.out.println();
        }
    }
    public static String captcha(){
        ArrayList<Integer> numbers;
        numbers = getRandomNumbers();
        printCaptcha(numbers);
        String string = new String();
        for (Integer number : numbers) {
            string += number;
        }
        return string;
    }


    public static boolean validType(String[] types, String type) {
        for (String s : types) {
            if (s.equals(type)) {
                return true;
            }
        }
        return false;
    }



    public static ArrayList<Block> getBlacksInRadius(int size, int x, int y, int radius, Map map){
        ArrayList<Block> blocks = new ArrayList<>();
        int diameter = radius * 2;
        for (int i = 0; i <= diameter; i++) {
            for (int j = 0; j <= diameter; j++) {
                int row = x - radius + i;
                int column = y - radius + j;
                if (row >= 0 && row < size && column >= 0 && column < size) {
                    blocks.add(map.getBlockByPosition(row, column));
                }
            }
        }
        return  blocks;
    }
}
