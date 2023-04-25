package project.controller;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.graalvm.compiler.core.common.type.ArithmeticOpTable;
import project.model.ApplicationManager;
import project.model.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SaveAndLoad {
    public static String Address="Database/Users.json";
    public static void GameInitialization(){
        ObjectMapper objectMapper=new ObjectMapper();
        try{
            ArrayList <User> raw= objectMapper.readValue(Files.readAllBytes(Paths.get(Address)), new TypeReference<ArrayList<User>>() {
            });
            ApplicationManager.setUsersList(raw);
        }catch (IOException e){
            System.out.println("Error in loading information!\nThe Data may be deleted!");
        }

    }
 public static void UsersSave(ArrayList<User> users){
     ObjectMapper objectMapper=new ObjectMapper();
     try{
         objectMapper.writeValue(new File(Address),users);
     }catch (IOException e){
         System.out.println("Error in saving!\nplease try again");
     }
 }
}
