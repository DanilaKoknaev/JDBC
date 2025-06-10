package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class UserServiceImpl implements UserService {
    private final UserDao userDAO;

    public UserServiceImpl() {
        try {
            this.userDAO = new UserDaoJDBCImpl();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize UserDao", e);
        }
    }


    public void createUsersTable() {
        try {
            userDAO.createUsersTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try {
            userDAO.dropUsersTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            userDAO.saveUser(name, lastName, age);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try {
            userDAO.removeUserById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {

        try {
            return userDAO.getAllUsers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cleanUsersTable() {
        try {
            userDAO.cleanUsersTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
