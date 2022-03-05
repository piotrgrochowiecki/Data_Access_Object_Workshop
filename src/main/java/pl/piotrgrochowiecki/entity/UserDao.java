package pl.piotrgrochowiecki.entity;

import org.mindrot.jbcrypt.BCrypt;
import pl.piotrgrochowiecki.DbUtil;

import java.sql.*;
import java.util.Arrays;

public class UserDao {

    private static final String DATABASE = "workshop2";
    private static final String DROP_DATABASE_QUERY = "DROP DATABASE IF EXISTS ";
    private static final String CREATE_DATABASE_QUERY = "CREATE DATABASE IF NOT EXISTS " + DATABASE + "CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;";

    private static final String CREATE_USER_QUERY = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
    private static final String READ_USER_BY_ID_QUERY = "SELECT * FROM users WHERE id = ?";
    private static final String UPDATE_USER_BY_ID_QUERY = "UPDATE users SET username = ?, email = ? WHERE id = ?";
    private static final String DELETE_USER_BY_ID_QUERY = "DELETE FROM users WHERE id = ?";
    private static final String READ_ALL_USERS_QUERY = "SELECT * FROM users";
    private static final String DELETE_ALL_USERS_QUERY = "DELETE FROM users";

    public User create(User user) {
        try (Connection conn = DbUtil.connect(DATABASE)) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.executeUpdate();
            //Pobieramy wstawiony do bazy identyfikator, a następnie ustawiamy id obiektu user.
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public User read(int userId) {
        try (Connection conn = DbUtil.connect(DATABASE)) {
            PreparedStatement statement = conn.prepareStatement(READ_USER_BY_ID_QUERY);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(User user){
        try (Connection conn = DbUtil.connect(DATABASE)) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_USER_BY_ID_QUERY);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setInt(3, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection conn = DbUtil.connect(DATABASE)) {
            PreparedStatement statement = conn.prepareStatement(DELETE_USER_BY_ID_QUERY);
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("User with id " + id + " has just been deleted!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAll() {
        try (Connection conn = DbUtil.connect(DATABASE)) {
            PreparedStatement statement = conn.prepareStatement(DELETE_ALL_USERS_QUERY);
            statement.executeUpdate();
            System.out.println("All users have just been deleted from database!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private User[] addToArray(User u, User[] users) {
        User[] tmpUsers = Arrays.copyOf(users, users.length + 1);
        tmpUsers[users.length] = u;
        return tmpUsers;
    }

    public User[] findAll() {
        try (Connection conn = DbUtil.connect(DATABASE)) {
            PreparedStatement statement = conn.prepareStatement(READ_ALL_USERS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            User[] users = new User[0];
            while (resultSet.next()) {
                int userId = resultSet.getInt("id");
                User user = read(userId);
                users = addToArray(user, users);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void dropDatabase() {
        try (Connection conn = DbUtil.connect(DATABASE)) {
            PreparedStatement statement = conn.prepareStatement(DROP_DATABASE_QUERY + DATABASE);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createDatabase() {
        try (Connection conn = DbUtil.connect(DATABASE)) {
            PreparedStatement statement = conn.prepareStatement(CREATE_DATABASE_QUERY);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
