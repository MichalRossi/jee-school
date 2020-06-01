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

@WebServlet("/ExerciseEdit")
public class ExerciseEdit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int exerciseId = Integer.parseInt(request.getParameter("exerciseId"));
        String exerciseTitle = request.getParameter("title");
        String exerciseDescription = request.getParameter("description");
        List<Exercise> exercises = new ArrayList<>();
        try  {
            exercises = ExerciseDao.loadAll(DbUtil.getConnection());
            for(Exercise element : exercises){
                if(element.getId() == exerciseId){
                    Exercise updateExercise = ExerciseDao.findById(DbUtil.getConnection(),exerciseId);
                    ExerciseDao.update(DbUtil.getConnection(), updateExercise, exerciseTitle, exerciseDescription);
                    exercises = ExerciseDao.loadAll(DbUtil.getConnection());
                    request.setAttribute("exercises",exercises);
                    request.getRequestDispatcher("exercises.jsp").forward(request, response);
                    return;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            int exerciseId = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("id",exerciseId);
            request.getRequestDispatcher("exerciseEdit.jsp").forward(request, response);

    }

}
