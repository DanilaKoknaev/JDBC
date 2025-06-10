package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    public UserDaoJDBCImpl() throws SQLException {

    }



    public void createUsersTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS `users` (`id` int NOT NULL AUTO_INCREMENT,`name` varchar(45) NOT NULL,`lastName` varchar(45) NOT NULL,`age` int NOT NULL, PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3";
        try (Connection connection = getConnection();
             Statement st = connection.prepareStatement(sql)) {

            st.executeUpdate(sql);
        }
    }

    public void dropUsersTable() throws SQLException {
        String sql = "DROP TABLE IF EXISTS users";
        try (Connection connection = getConnection();
             Statement st = connection.prepareStatement(sql);) {

            st.executeUpdate(sql);
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        String sql = "INSERT INTO Users (name, lastName, age) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement pr =connection.prepareStatement(sql)) {


            pr.setString(1, name);
            pr.setString(2, lastName);
            pr.setByte(3, age);
            pr.executeUpdate();

        }
    }

    public void removeUserById(long id) throws SQLException {
        String sql = "DELETE FROM Users WHERE id=?";
        User user = new User();
        try (Connection connection = getConnection();
             PreparedStatement pr = connection.prepareStatement(sql)) {
            pr.setLong(1, id);
            pr.executeUpdate();
        }
    }

    public List<User> getAllUsers() throws SQLException {

        List <User> userList = new ArrayList<>();
        String sql = "SELECT id, name, lastName, age FROM Users";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
        }
        return userList;
    }

    public void cleanUsersTable() throws SQLException {
        String sql = "DELETE FROM users";
        try (Connection connection = getConnection();
             Statement st = connection.prepareStatement(sql)) {
             st.executeUpdate(sql);
        }
    }


}
