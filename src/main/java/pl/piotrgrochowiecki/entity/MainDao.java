package pl.piotrgrochowiecki.entity;

import java.util.Arrays;

public class MainDao {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        userDao.dropDatabase();
        userDao.createDatabase();
        userDao.createUsersTable();

        User user1 = new User("testUser1", "user1@test.com", "admin");
        userDao.create(user1);
        System.out.println("User 1 = " + userDao.read(1).toString());

        User user2 = new User("testUser2", "user2@test.com", "admin");
        userDao.create(user2);
        System.out.println("User 2 before update = " + userDao.read(2).toString());

        user2.setEmail("user2B@test.com");
        user2.setUserName("testUser2B");
        userDao.update(user2);
        System.out.println("User 2 after update = " + userDao.read(2).toString());

        User user3 = new User("testUser3", "user3@test.com", "admin");
        userDao.create(user3);
        System.out.println("user3.getPassword() = " + user3.getPassword());
        user3.setPassword("admin123");
        userDao.updatePassword(user3);
        System.out.println("user3.getPassword() = " + user3.getPassword());

        User[] users = userDao.findAll();
        System.out.println(Arrays.toString(users));

//        userDao.delete(3);

        userDao.findAll();
        System.out.println(Arrays.toString(users));

//        userDao.deleteAll();
    }
}
