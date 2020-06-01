package pl.coderslab.jeeschool.controller;

import pl.coderslab.jeeschool.dao.ExerciseDao;
import pl.coderslab.jeeschool.dao.UserGroupDao;
import pl.coderslab.jeeschool.model.Exercise;
import pl.coderslab.jeeschool.model.UserGroup;
import pl.coderslab.jeeschool.util.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ExerciseAdd")
public class ExerciseAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newExerciseTitle = request.getParameter("exerciseTitle");
        String newExerciseDescription = request.getParameter("exerciseDescription");
        Exercise newExercise = new Exercise();
        List<Exercise> exercises = new ArrayList<>();

        try {
            newExercise = ExerciseDao.create(DbUtil.getConnection(), newExerciseTitle, newExerciseTitle);
            exercises = ExerciseDao.loadAll(DbUtil.getConnection());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("exercises",exercises);
        request.getRequestDispatcher("exercises.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Exercise> exercises = new ArrayList<>();
        try  {
            exercises = ExerciseDao.loadAll(DbUtil.getConnection());


        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("exercises",exercises);
        request.getRequestDispatcher("exerciseAdd.jsp").forward(request, response);

    }

}
