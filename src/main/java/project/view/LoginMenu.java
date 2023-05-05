package project.view;

import project.controller.Commands;
import project.controller.LoginMenuController;
import project.controller.RegisterMenuController;
import project.model.ApplicationManager;
import project.model.Tools;

import javax.annotation.processing.SupportedSourceVersion;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu {
  private final static Scanner scanner = Menu.getScanner();

  public static void run() throws InterruptedException, NoSuchAlgorithmException {
     Matcher matcher;
     boolean inThisMenu=true;
     int delay=0;
     System.out.println("**<< Login Menu >>**");
    while(inThisMenu){
      String command = scanner.nextLine();
      String output;
      if(command.matches(Commands.LOGIN.getRegex()) || command.matches(Commands.LOGIN_LOGGED_IN.getRegex())){
        matcher=Menu.getMatcher(command,Commands.LOGIN.getRegex());
        output=LoginMenuController.Login(matcher);
        System.out.println(output);
        if(output.equals("Error: password doesn't match!")){
          delay++;
          Thread.sleep(delay * 5000);
          System.out.println("You can now try again now.");
        }
        else{
          delay=0;
          if(command.matches(Commands.LOGIN_LOGGED_IN.getRegex()))
            ApplicationManager.setStayLoggedIn(true);
          MainMenu.run();
        }
      }
      else if(command.matches(Commands.FORGET_PASSWORD.getRegex())){
        System.out.println("Enter your username:");
        String comm=Menu.getScanner().nextLine();
        matcher=Menu.getMatcher(comm,"(?<username>[a-zA-Z0-9_]+");
        output=LoginMenuController.ForgetPassword(matcher);
        if(output != null){
          System.out.println(output);
        }
        else{
          System.out.println("enter new password:");
          String newPassword=Menu.getScanner().nextLine();
          String result= Tools.passwordWeakCheck(newPassword);
          System.out.println(result);
          if(result.contains("logged"))
            MainMenu.run();
        }
      }
      else if(command.equals("user logout")){
        return;
      }
      else if(command.equals("register menu")){
        inThisMenu=false;
        RegisterMenu.run();
      }
      else{
        System.out.println("Invalid command");
      }
    }
  }

}
