package pl.piotrgrochowiecki.entity;

import java.util.Arrays;

public class MainDao {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();
//        userDao.dropDatabase();
//        userDao.createDatabase();
//        userDao.createUsersTable();

        User user1 = new User();
        user1.setUserName("robertos32904");
        user1.setEmail("robert121@wp.pl");
        user1.setPassword("piesek123");
        userDao.create(user1);
        System.out.println("User 1 = " + userDao.read(1).toString());

        User user2 = new User();
        user2.setUserName("Marek12");
        user2.setEmail("biegacz432@gmail.com");
        user2.setPassword("bieganie321");
        userDao.create(user2);
        System.out.println("User 2 before update = " + userDao.read(2).toString());

        user2.setEmail("rowerzysta150@yahoo.com");
        user2.setUserName("Pioter4");
        userDao.update(user2);
        System.out.println("User 2 after update = " + userDao.read(2).toString());

        User user3 = new User();
        user3.setUserName("Marysia38");
        user3.setEmail("mr8244@gmail.com");
        user3.setPassword("test3212");
        userDao.create(user3);

        User[] users = userDao.findAll();
        System.out.println(Arrays.toString(users));

        userDao.delete(3);

        userDao.findAll();
        System.out.println(Arrays.toString(users));

//        userDao.deleteAll();
    }
}
