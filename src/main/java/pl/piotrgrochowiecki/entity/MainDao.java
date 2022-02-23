package pl.piotrgrochowiecki.entity;

public class MainDao {
    public static void main(String[] args) {
        User testUser1 = new User();
        testUser1.setUserName("robertos32904");
        testUser1.setEmail("robert121@wp.pl");
        testUser1.setPassword("pies123");

        UserDao userDao1 = new UserDao();
        userDao1.create(testUser1);
    }
}
