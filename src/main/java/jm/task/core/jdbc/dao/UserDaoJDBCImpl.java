package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS Users (" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL," +
                "last_name VARCHAR(255) NOT NULL," +
                "age TINYINT UNSIGNED NOT NULL)";

        try(Connection connection = Util.getMyConnection();
            Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS Users";

        try(Connection connection = Util.getMyConnection();
            Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {

        String sql = "INSERT INTO Users (name, last_name, age) VALUES (?,?,?)";

        try(Connection connection = Util.getMyConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM Users WHERE id=?";

        try(Connection connection = Util.getMyConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {

        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM Users";

        try(Connection connection = Util.getMyConnection();
        Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {

            while(resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {

        String sql = "DELETE FROM Users";

        try(Connection connection = Util.getMyConnection();
        Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
