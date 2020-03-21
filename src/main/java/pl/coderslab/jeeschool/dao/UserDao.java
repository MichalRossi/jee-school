package pl.coderslab.jeeschool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pl.coderslab.jeeschool.model.User;
import pl.coderslab.jeeschool.model.UserGroup;

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


}
