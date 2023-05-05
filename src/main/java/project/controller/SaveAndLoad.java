package project.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import project.model.ApplicationManager;
import project.model.Game;
import project.model.Map;
import project.model.User;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class SaveAndLoad {
    public static String getAddress1() {
        return Address1;
    }

    public static void setAddress1(String address1) {
        Address1 = address1;
    }

    public static String getAddress2() {
        return Address2;
    }

    public static void setAddress2(String address2) {
        Address2 = address2;
    }

    public static String getAddress3() {
        return Address3;
    }

    public static void setAddress3(String address3) {
        Address3 = address3;
    }

    public static String Address1="Database/Users.json";
    public static String Address2="Database/Maps.json";
    public static String Address3="Database/Games.json";

    public static void gameInitialization(){
        loadUsers();
        loadGames();
        loadMaps();
    }

    public static void save(ArrayList<User> users,ArrayList<Map> maps,ArrayList<Game> games){
        SaveAndLoad.saveUsers(users);
        SaveAndLoad.saveGames(games);
        SaveAndLoad.saveMaps(maps);
    }

    public static void saveUsers(ArrayList<User> users) {
        Gson gson = new Gson();
        String json = gson.toJson(users);
        try (FileWriter file = new FileWriter(Address1)) {
            file.write(json);
        } catch (IOException e) {
            System.out.println("Error in saving!\nplease try again");
        }
    }

    public static void saveMaps(ArrayList<Map> maps) {
        Gson gson = new Gson();
        String json = gson.toJson(maps);
        try (FileWriter file = new FileWriter(Address2)) {
            file.write(json);
        } catch (IOException e) {
            System.out.println("Error in saving!\nplease try again");
        }
    }

    public static void saveGames(ArrayList<Game> games) {
        Gson gson = new Gson();
        String json = gson.toJson(games);
        try (FileWriter file = new FileWriter(Address3)) {
            file.write(json);
        } catch (IOException e) {
            System.out.println("Error in saving!\nplease try again");
        }
    }



    private static void loadUsers() {
        ArrayList<User> users;
        try (FileReader file = new FileReader(Address1)) {
            Type gameListType = new TypeToken<ArrayList<User>>(){}.getType();
            Gson gson = new Gson();
            users = gson.fromJson(file, gameListType);
            if (users!=null)
                ApplicationManager.setUsersList(users);
        }
        catch (IOException e) {
            System.out.println("Error in loading information!\nThe Data may be deleted!");
        }
    }


    private static void loadMaps() {
        ArrayList<Map> maps;
        try (FileReader file = new FileReader(Address2)) {
            Type gameListType = new TypeToken<ArrayList<Map>>(){}.getType();
            Gson gson = new Gson();
            maps = gson.fromJson(file, gameListType);
            if (maps!=null)
                ApplicationManager.setMapsList(maps);

        }
        catch (IOException e) {
            System.out.println("Error in loading information!\nThe Data may be deleted!");
        }
    }

    private static void loadGames() {
        ArrayList<Game> games;
        try (FileReader file = new FileReader(Address3)) {
            Type gameListType = new TypeToken<ArrayList<User>>(){}.getType();
            Gson gson = new Gson();
            games = gson.fromJson(file, gameListType);
            if (games!=null)
                ApplicationManager.setGamesList(games);
        }
        catch (IOException e) {
            System.out.println("Error in loading information!\nThe Data may be deleted!");
        }
    }

}
