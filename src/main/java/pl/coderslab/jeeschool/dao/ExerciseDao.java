package pl.coderslab.jeeschool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pl.coderslab.jeeschool.model.Exercise;

public class ExerciseDao {

    public static Exercise findById(Connection connection, int id) throws SQLException {
        String query = "SELECT * FROM Exercises where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Exercise loadedExercise = new Exercise();
            loadedExercise.setId(resultSet.getInt("id"));
            loadedExercise.setTitle(resultSet.getString("title"));
            loadedExercise.setDescription(resultSet.getString("description"));
            return loadedExercise;
        }
        return null;
    }


}
