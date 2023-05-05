package project;

import org.junit.jupiter.api.Assertions;
import project.model.User;

public class AppTest {
    @org.junit.jupiter.api.Test
    public void RegisterTest () {

        User user1 =new User("1","1","1","1","1","1",1);
        User user2=new User("1","1","1","1","1","1",1);
        Assertions.assertEquals(user1,user2);

    }
}
