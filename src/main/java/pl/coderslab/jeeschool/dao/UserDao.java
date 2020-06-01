package pl.coderslab.jeeschool.dao;

import pl.coderslab.jeeschool.model.User;
import pl.coderslab.jeeschool.model.UserGroup;
import pl.coderslab.jeeschool.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public static User findById(Connection connection, int id) throws SQLException {
        String query = "SELECT * FROM Users where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return getUserFromResultSet(connection, resultSet);
        }
        return null;
    }


    public static List<User> loadAllByGroupId(Connection connection, int groupId) throws SQLException {
        String query = "SELECT * FROM Users where user_group_id=?";
        List<User> users = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, groupId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            users.add(getUserFromResultSet(connection,resultSet));
        }
        return users;
    }


    public static List<User> loadAll(Connection connection) throws SQLException {
        String query = "SELECT * FROM Users";
        List<User> users = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            users.add(getUserFromResultSet(connection,resultSet));
        }
        return users;
    }


    private static User getUserFromResultSet(Connection connection, ResultSet resultSet) throws SQLException {
        User loadedUser = new User();
        loadedUser.setId(resultSet.getInt("id"));
        loadedUser.setUsername(resultSet.getString("username"));
        loadedUser.setPassword(resultSet.getString("password"));
        loadedUser.setEmail(resultSet.getString("email"));
        int userGroupId = resultSet.getInt("user_group_id");
        UserGroup userGroup = UserGroupDao.findById(connection, userGroupId);
        loadedUser.setUserGroup(userGroup);
        return loadedUser;
    }

    public static User create(Connection connection,String name, String email, String password, String groupName) throws SQLException {
        String sql = "INSERT INTO Users(username,email,password,user_group_id) VALUES (?,?,?,?)";

        User newUser = new User();
        List<UserGroup> groups = UserGroupDao.loadAllGroups(DbUtil.getConnection());
        int groupId = 0;

        for(UserGroup item : groups){
            if(item.getName().equals(groupName)){
                groupId = item.getId();
                break;
            }
        }

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setInt(4, groupId);
            preparedStatement.executeUpdate();
            newUser.setUsername(name);
            newUser.setEmail(email);
            newUser.setPassword(password);

            UserGroup userGroup = new UserGroup(groupName);
            newUser.setUserGroup(userGroup);

            return newUser;

    }

    public static void delete(Connection connection,User user) throws SQLException {
        String sql = "DELETE FROM Users where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, user.getId());
        preparedStatement.executeUpdate();

    }

    public static User update(Connection connection,User user, String name,String email, String password, UserGroup userGroup) throws SQLException {
        String sql = "UPDATE Users SET username = ?, email = ?, password = ?, user_group_id = ? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, email);
        preparedStatement.setString(3, password);
        preparedStatement.setInt(4, userGroup.getId());
        preparedStatement.setInt(5, user.getId());
        preparedStatement.executeUpdate();
        return user;
    }


}
