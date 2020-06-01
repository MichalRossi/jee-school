package pl.coderslab.jeeschool.controller;

import pl.coderslab.jeeschool.dao.ExerciseDao;
import pl.coderslab.jeeschool.model.Exercise;
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

@WebServlet("/ExerciseDelete")
public class ExerciseDelete extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int exerciseId = Integer.parseInt(request.getParameter("id"));
        List<Exercise> exercises = new ArrayList<>();
        Exercise exerciseDeleted = new Exercise();

        try {
            exerciseDeleted = ExerciseDao.findById(DbUtil.getConnection(), exerciseId);
            ExerciseDao.delete(DbUtil.getConnection(), exerciseDeleted);
            exercises = ExerciseDao.loadAll(DbUtil.getConnection());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("exercises", exercises);
        request.getRequestDispatcher("exercises.jsp").forward(request, response);

    }

}
