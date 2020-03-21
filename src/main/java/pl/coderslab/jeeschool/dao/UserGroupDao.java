package pl.coderslab.jeeschool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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


}
