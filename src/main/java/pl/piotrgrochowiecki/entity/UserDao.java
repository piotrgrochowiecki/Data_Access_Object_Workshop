package pl.piotrgrochowiecki.entity;

public class UserDao {

    private static final String CREATE_USER_QUERY = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
    private static final String READ_USER_BY_ID_QUERY = "SELECT * FROM users WHERE id = ?";
    private static final String UPDATE_USER_BY_ID_QUERY = "UPDATE users SET email = ?, username = ? WHERE id = 1";
    private static final String DELETE_USER_BY_ID_QUERY = "DELETE FROM users WHERE id = ?";

}
