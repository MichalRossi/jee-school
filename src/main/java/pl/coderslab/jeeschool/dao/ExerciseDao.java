package pl.coderslab.jeeschool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pl.coderslab.jeeschool.model.Exercise;
import pl.coderslab.jeeschool.model.User;

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

    public static List<Exercise> loadAll(Connection connection) throws SQLException {
        String query = "SELECT * FROM Exercises";
        List<Exercise> exercises = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            exercises.add(getExerciseFromResultSet(connection,resultSet));
        }
        return exercises;
    }

    private static Exercise getExerciseFromResultSet(Connection connection, ResultSet resultSet) throws SQLException {
        Exercise loadedExercise = new Exercise();
        loadedExercise.setId(resultSet.getInt("id"));
        loadedExercise.setTitle(resultSet.getString("title"));
        loadedExercise.setDescription(resultSet.getString("description"));
        return loadedExercise;
    }

    public static Exercise update(Connection connection,Exercise exercise, String title, String description) throws SQLException {
        String sql = "UPDATE Exercises SET title = ?, description = ?  where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, title);
        preparedStatement.setString(2, description);
        preparedStatement.setInt(3, exercise.getId());
        preparedStatement.executeUpdate();

        return exercise;
    }

    public static void delete(Connection connection,Exercise exercise) throws SQLException {
        String sql = "DELETE FROM Exercises where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, exercise.getId());
        preparedStatement.executeUpdate();

    }

    public static Exercise create(Connection connection,String title, String description) throws SQLException {
        String sql = "INSERT INTO Exercises(title, description) VALUES (?,?)";
        Exercise exercise = new Exercise();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, title);
        preparedStatement.setString(2, description);
        preparedStatement.executeUpdate();

        exercise.setDescription(description);
        exercise.setTitle(title);

        return exercise;

    }

}
