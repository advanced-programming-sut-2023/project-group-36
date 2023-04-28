package project.model;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {
    
    public static ArrayList<String> splitString(String input) {
        ArrayList<String> parts = new ArrayList<>();
        Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(input);
        while (m.find()) {
            parts.add(m.group(1).replace("\"", ""));
        }
        return parts;
    }

    public static String removeString(String str1, String str2) {
        if (str2.startsWith(str1)) {
            return str2.substring(str1.length());
        } 
        else {
            return "Error";
        }
    }

    public static String commandFormating(ArrayList<String> arr1, ArrayList<String> arr2) {
        String result = new String();
        ArrayList<String> arr3 = new ArrayList<>();
        for (int i = 0; i < arr1.size(); i++) {
            for (int j = 0; j < arr2.size(); j++) {
                if (arr1.get(i).equals(arr2.get(j))) {
                    arr3.add(arr1.get(i));
                    break;
                }
            }
        }
        for (int i = 0; i < arr3.size(); i++) {
            String item = arr3.get(i);
            int index = arr2.indexOf(item);
            result+=item+" ";
            if (index != -1 && index < arr2.size() - 1 && !arr3.contains(arr2.get(index + 1))) {
                result+=(arr2.get(index+1)+" ");
            } 
            else {
                result+=("");
            }
        }
        return result;
    }

    private static String[] print0_9AsciiArt(int number) {
        String[] asciiForm = new String[7];
        switch (number) {
            case 0:
                asciiForm[0]=getRandomString(8);
                asciiForm[1]="..####..";
                asciiForm[2]=".##..##.";
                asciiForm[3]=".######.";
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
            for (int j = 0; j < nums.size(); j++) {
                System.out.print(getRandomString(2));
                System.out.print(print0_9AsciiArt(nums.get(j))[i]);
                System.out.print(getRandomString(2));
            }
            System.out.println();
        }
    }
    public static ArrayList<Integer> captcha(){
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers = getRandomNumbers();
        printCaptcha(numbers);
        return numbers;
    }


    public static boolean validType(String[] types, String type) {
        for (String s : types) {
            if (s.equals(type)) {
                return true;
            }
        }
        return false;
    }
}
