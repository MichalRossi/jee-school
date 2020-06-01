package pl.coderslab.jeeschool.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;


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



    public static Solution getSolutionDetails(Connection connection, int id) {
        String query =  "SELECT * FROM Solutions where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Solution solution = getSolutionFromResultSet(connection,resultSet);
                return solution;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Solution> getUserSolution(Connection connection, int id) {
        String query =  "SELECT * FROM Solutions where user_id = ?";
        List<Solution> userSolutions = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Solution solution = getSolutionFromResultSet(connection,resultSet);
                userSolutions.add(solution);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userSolutions;
    }

    public static Solution create(Connection connection, String description, Exercise exercise, User user) throws SQLException {
        String query =  "INSERT INTO Solutions(created,updated,description,exercise_id,user_id) VALUES (?,?,?,?,?)";
        Date date = new Date();
        Object param = new java.sql.Timestamp(date.getTime());
        Solution newSolution = new Solution();

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setObject(1, param);
        statement.setObject(2, param);
        statement.setString(3, description);
        statement.setInt(4, exercise.getId());
        statement.setInt(5, user.getId());
        statement.executeUpdate();

        newSolution.setCreated(date);
        newSolution.setUpdated(date);
        newSolution.setDescription(description);
        newSolution.setExercise(exercise);
        newSolution.setUser(user);

        return newSolution;
    }

    public static void delete(Connection connection,Solution solution) throws SQLException {
        String sql = "DELETE FROM Solutions where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, solution.getId());
        preparedStatement.executeUpdate();

    }

    public static Solution update(Connection connection, Solution solution, String description) throws SQLException {
        String sql = "UPDATE Solutions SET updated = ?, description = ? where id = ?";

        Date date = new Date();
        Object param = new java.sql.Timestamp(date.getTime());

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, param);
        preparedStatement.setString(2, description);
        preparedStatement.setInt(3, solution.getId());
        preparedStatement.executeUpdate();

        return solution;
    }


}
