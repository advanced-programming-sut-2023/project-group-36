package project.view;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Menu {
    private static final Scanner scanner = new Scanner(System.in);

    public static Matcher getMatcher(String command, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(command);
        return matcher.matches() ? matcher : null;
    }
    public static Scanner getScanner() {
        return scanner;
    }
}

