package project.controller;

import project.model.ApplicationManager;
import project.model.Tools;
import project.model.User;
import project.view.Menu;

import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.regex.Matcher;

public class RegisterMenuController {

    private static String SuggestUsername(String username) {
        String suggested = username;
        int number = 1;
        while (ApplicationManager.getUserByUsername(suggested)!=null){
            suggested = username +"_" + number;
            number ++;
        }
        return suggested;
    }

    private static String usernameCheck(String username){
        if (!username.matches("[A-Za-z0-9_]+")){
            return "Invalid username format!";
        }
        if (ApplicationManager.getUserByUsername(username)!=null){
            System.out.println("A user with this username exists!" + "\nSuggested username: " + SuggestUsername(username));
            System.out.println("If you want to create your account with the suggested username,enter the number 1\nand otherwise, enter any other character to return to the registration menu:");
            String input = Menu.getScanner().nextLine();
            if (!input.equals("1")){
                return "Register canceled!";
            }
        }
        return "Good";
    }


    public static String register(Matcher matcher) throws NoSuchAlgorithmException {
        String username = matcher.group("username");
        String password = matcher.group("password");
        String passwordConfirmation = matcher.group("passwordConfirmation");
        String email = matcher.group("email");
        String nickname = matcher.group("nickname");
        String slogan = matcher.group("slogan")!=null ? matcher.group("slogan") : null;
        String result = checker(matcher);
        if (!result.equals("Good")){
            return "The field" + result + " is null!";
        }
        if (password.equals("random")){
            password = randomPassword();
        }
        if (slogan != null && slogan.equals("random")){
            slogan = randomSlogan();
        }
        result = usernameCheck(username);
        if (!result.equals("Good")){
            return result;
        }
        if (!Tools.passwordWeakCheck(password).equals("Good")){
            return Tools.passwordWeakCheck(password);
        }
        if (!password.equals(passwordConfirmation)){
            return "Error: password and confirmation password are not the same!";
        }
        if (ApplicationManager.getUserByEmail(email)!=null){
            return "A user with this Email exists!";
        }
        if (!email.matches("[A-Za-z0-9._]+@[A-Za-z0-9._]+.[A-Za-z0-9._]+")){
            return "Invalid Email format!";
        }
        result = securityQuestion();
        if (!result.matches("[123].*")){
            return result;
        }
        String questionAnswer = result.substring(1);
        int questionNumber = Integer.parseInt(String.valueOf(result.charAt(0)));
        String captchaNumbers = Tools.captcha();
        while (!Menu.getScanner().nextLine().equals(captchaNumbers)){
            System.out.println("Error: You entered the CAPTCHA code incorrectly.");
            captchaNumbers = Tools.captcha();
        }

        User user = new User(username, password, nickname, email,slogan, questionAnswer, questionNumber);
        ApplicationManager.addUser(user);
        user.setPassword(SHA_256Format.sha256(user.getPassword()));
        return "Your registration was successful. To enter, go to the login menu!";
    }

    private static String checker(Matcher matcher) {
        String[] strings = new String[]{"username", "password","passwordConfirmation","email","nickname","slogan"};
        for (int i = 0; i < matcher.groupCount(); i++) {
            if (matcher.group(i)!=null && !matcher.group(i).matches(".+")){
                return strings[i];
            }
        }
        return "Good";
    }


    private static String randomPassword(){
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$%^&*()_+";
        String numbers = "0123456789";
        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
        Random random = new Random();
        int length = random.nextInt(25);
        char[] password = new char[length];
        password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
        password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
        password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
        password[3] = numbers.charAt(random.nextInt(numbers.length()));
        for(int i = 4; i< length ; i++) {
            password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
        }
        return String.valueOf(password);

    }

    private static String randomSlogan(){
        Random random = new Random();
        int number = random.nextInt(4);
        String[] slogans = {"hard but possible!","powerful!","I'm god!","best!"};
        return slogans[number];
    }


    private static String securityQuestion(){
        System.out.println("Pick your security question: 1. What is my father’s name? 2. What\n" +
                "was my first pet’s name? 3. What is my mother’s last name?");
        String input = Menu.getScanner().nextLine();
        while (!input.matches(Commands.QUESTION_ANSWER.getRegex())){
            System.out.println("The format of your Answer is invalid! please enter again: (or send \"cancel\" for cancel register!)");
            input = Menu.getScanner().nextLine();
            if (input.equals("cancel")){
                return "Register canceled!";
            }
        }
        Matcher matcher = Menu.getMatcher(input,Commands.QUESTION_ANSWER.getRegex());
        int questionNumber = Integer.parseInt(matcher.group(1));
        String questionAnswer = matcher.group(2);
        String answerConfirm = matcher.group(3);
        if (!questionAnswer.equals(answerConfirm)){
            return "Error: Answers are not the same!";
        }
        if (questionNumber!=1 && questionNumber!=2 && questionNumber!=3){
            return "Error: Number isn't correct!";
        }
        return questionNumber+questionAnswer;
    }
}
