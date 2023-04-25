package project.view;

import project.controller.Commands;
import project.controller.LoginMenuController;
import project.controller.RegisterMenuController;
import project.model.ApplicationManager;

import javax.annotation.processing.SupportedSourceVersion;
import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu {
  private final static Scanner scanner = Menu.getScanner();

  public static void run() throws InterruptedException {
    Matcher matcher;
     Boolean inThisMenu=true;
    int delay=0;
    System.out.println("**<< Login Menu >>**");
    while(true){
      String command = scanner.nextLine();
      String output;
      if(command.matches(Commands.LOGIN.getRegex()) || command.matches(Commands.LOGIN_LOGGED_IN.getRegex())){
        matcher=Menu.getMatcher(command,Commands.LOGIN.getRegex());
        output=LoginMenuController.Login(matcher);
        if(output.contains("match")){
          delay++;
          Thread.sleep(delay * 5000);
          System.out.println("You can now try again now.");
        }
        else{
          delay=0;
          if(command.matches(Commands.LOGIN_LOGGED_IN.getRegex())){
            command.matches(Commands.LOGIN_LOGGED_IN.getRegex());
          }
          MainMenu.run();
        }
      }
      else if(command.matches(Commands.FORGET_PASSWORD.getRegex())){
        matcher=Menu.getMatcher(command,Commands.FORGET_PASSWORD.getRegex());
        output=LoginMenuController.ForgetPassword(matcher);
        if(output != null)
          System.out.println(output);
        else{
          System.out.println("enter new password:");
          String newPassword=Menu.getScanner().nextLine();
          String result=LoginMenuController.passwordWeakCheck(newPassword);
          System.out.println(result);
        }
      }
      else if(command.equals("user logout")){
        inThisMenu=false;
        return;
      }
      else{
        System.out.println("Invalid command");
      }
    }
  }

}
