package Chat;

import javafx.application.Application;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainMenu extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/aa","root","Group36@");
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery("select username from userdata");
        while (resultSet.next()){
            System.out.println(resultSet.getString("username"));
        }

    }
}
