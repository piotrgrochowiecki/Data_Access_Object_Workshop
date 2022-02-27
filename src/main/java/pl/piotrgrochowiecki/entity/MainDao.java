package pl.piotrgrochowiecki.entity;

public class MainDao {
    public static void main(String[] args) {
//        User testUser1 = new User();
//        testUser1.setUserName("robertos32904");
//        testUser1.setEmail("robert121@wp.pl");
//        testUser1.setPassword("pies123");
//
//        UserDao userDao1 = new UserDao();
//        userDao1.create(testUser1);

//        UserDao userDao2 = new UserDao();
//        System.out.println("userDao2.read(4).toString() = " + userDao2.read(4).toString());


        User user3 = new User();
//        user3.setUserName("Marek12");
//        user3.setEmail("biegacz432@gmail.com");
//        user3.setPassword("sfdvgdbdbvd");

        UserDao userDao3 = new UserDao();
        //userDao3.create(user3);

        System.out.println("userDao3.read(5).toString() = " + userDao3.read(5).toString());

        user3.setEmail("rowerzysta150@yahoo.com");
        user3.setUserName("Pioter4");
        userDao3.update(user3);

        System.out.println("userDao3.read(5).toString() = " + userDao3.read(5).toString());

        userDao3.delete(5);


    }
}
