package pl.coderslab.jeeschool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import pl.coderslab.jeeschool.model.UserGroup;

public class UserGroupDao {

    public static UserGroup findById(Connection connection, int id) throws SQLException {
        String sql = "SELECT * FROM Users_Groups where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            UserGroup loadedUserGroup = new UserGroup();
            loadedUserGroup.setId(resultSet.getInt("id"));
            loadedUserGroup.setName(resultSet.getString("name"));
            return loadedUserGroup;
        }
        return null;
    }

    public static UserGroup findByName(Connection connection, String groupName) throws SQLException {
        String sql = "SELECT * FROM Users_Groups where name=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, groupName);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            UserGroup loadedUserGroup = new UserGroup();
            loadedUserGroup.setId(resultSet.getInt("id"));
            loadedUserGroup.setName(resultSet.getString("name"));
            return loadedUserGroup;
        }
        return null;
    }

    public static List<UserGroup> loadAllGroups(Connection connection) throws SQLException {
        String sql = "SELECT * FROM Users_Groups";
        List<UserGroup> userGroups = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            UserGroup loadedUserGroup = new UserGroup();
            loadedUserGroup.setId(resultSet.getInt("id"));
            loadedUserGroup.setName(resultSet.getString("name"));
            userGroups.add(loadedUserGroup);
        }
        return userGroups;
    }

    public static UserGroup create(Connection connection,String name) throws SQLException {
        String sql = "INSERT INTO Users_Groups(name) VALUES (?)";
        UserGroup userGroup = new UserGroup(name);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.executeUpdate();
        return userGroup;

    }

    public static UserGroup update(Connection connection,UserGroup group, String name) throws SQLException {
        String sql = "UPDATE Users_Groups SET name = ?  where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, group.getId());
        preparedStatement.executeUpdate();
        return group;
    }

    public static void delete(Connection connection,UserGroup group) throws SQLException {
        String sql = "DELETE FROM Users_Groups where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, group.getId());
        preparedStatement.executeUpdate();

    }

}





