package pl.coderslab.jeeschool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pl.coderslab.jeeschool.model.Exercise;
import pl.coderslab.jeeschool.model.Solution;
import pl.coderslab.jeeschool.model.User;

public class SolutionDao {

    public static List<Solution> findRecent(Connection connection, int limit) {
        List<Solution> solutions = new ArrayList<>();
        String query = "SELECT * FROM Solutions order by created desc limit ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, limit);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                solutions.add(getSolutionFromResultSet(connection, resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return solutions;
    }

    private static Solution getSolutionFromResultSet(Connection connection, ResultSet resultSet) throws SQLException {
        Solution loadedSolution = new Solution();
        loadedSolution.setId(resultSet.getInt("id"));
        loadedSolution.setCreated(resultSet.getDate("created"));
        loadedSolution.setUpdated(resultSet.getDate("updated"));
        loadedSolution.setDescription(resultSet.getString("description"));
        Exercise exercise = ExerciseDao.findById(connection, resultSet.getInt("exercise_id"));
        loadedSolution.setExercise(exercise);
        User user = UserDao.findById(connection, resultSet.getInt("user_id"));
        loadedSolution.setUser(user);
        return loadedSolution;
    }


}
