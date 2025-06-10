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
        Connection connection = getConnection();
        Statement st = null;
        String sql = "CREATE TABLE IF NOT EXISTS `users` (`id` int NOT NULL AUTO_INCREMENT,`name` varchar(45) NOT NULL,`lastName` varchar(45) NOT NULL,`age` int NOT NULL, PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3";
        try {
            st = connection.prepareStatement(sql);
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                st.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void dropUsersTable() throws SQLException {
        Connection connection = getConnection();
        Statement st = null;
        String sql = "DROP TABLE IF EXISTS users";
        try {
            st = connection.prepareStatement(sql);
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                st.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement pr = null;
        String sql = "INSERT INTO Users (name, lastName, age) VALUES (?, ?, ?)";
        try {
            pr = connection.prepareStatement(sql);

            pr.setString(1, name);
            pr.setString(2, lastName);
            pr.setByte(3, age);
            pr.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pr != null) {
                pr.close();
            }
            if (connection != null) {
                connection.close();
            }
        }



    }

    public void removeUserById(long id) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement pr = null;
        String sql = "DELETE FROM Users WHERE id=?";
        User user = new User();
        try {
            pr = connection.prepareStatement(sql);
            pr.setLong(1, id);
            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (pr != null) {
                pr.close();
            }
            if (connection != null) {
                connection.close();
            }
        }




    }

    public List<User> getAllUsers() throws SQLException {
        Connection connection = getConnection();
        List <User> userList = new ArrayList<>();
        String sql = "SELECT id, name, lastName, age FROM Users";
        Statement statement = null;
        try {
            statement =connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return userList;
        //return null;
    }

    public void cleanUsersTable() throws SQLException {
        Connection connection = getConnection();
        Statement st = null;
        String sql = "DELETE FROM users";
        try {
            st = connection.prepareStatement(sql);
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                st.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }


}
