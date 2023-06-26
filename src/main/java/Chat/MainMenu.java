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
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/chatsystem","root","Group36@");
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery("select username from userdata");
        while (resultSet.next()){
            String in=resultSet.getString("username");
            System.out.println(in);
        }
        statement.executeUpdate("insert into userdata(username,password,nickname,email,slogan) values ('majid','1234560','king','ffffffff','fuck you')");
        ResultSet resultSet1=statement.executeQuery("select slogan from userdata");
        while (resultSet1.next())
             System.out.println(resultSet1.getString("slogan"));

    }
}
