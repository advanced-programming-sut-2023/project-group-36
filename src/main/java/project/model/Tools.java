package project.model;

public class Tools {
    public static String CheckStringDoubleQut(String input){
        if (input.matches("\\S+")){
            return input;
        }
        if (input.matches("\".+\"")){
            return input.substring(1,input.length()-2);
        }
        return null;
    }
}
