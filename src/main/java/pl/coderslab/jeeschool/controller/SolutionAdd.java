package pl.coderslab.jeeschool.controller;

import pl.coderslab.jeeschool.dao.ExerciseDao;
import pl.coderslab.jeeschool.dao.SolutionDao;
import pl.coderslab.jeeschool.dao.UserDao;
import pl.coderslab.jeeschool.model.Exercise;
import pl.coderslab.jeeschool.model.Solution;
import pl.coderslab.jeeschool.model.User;
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

@WebServlet("/SolutionAdd")
public class SolutionAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String description = request.getParameter("description");
        int exerciseId = Integer.parseInt(request.getParameter("exerciseId"));
        int userId = Integer.parseInt(request.getParameter("userId"));

        Solution newSolution = new Solution();

        String numberSolutions = getServletContext().getInitParameter("number-solutions");
        List<Solution> solutions = new ArrayList<>();

        try {
            User user = UserDao.findById(DbUtil.getConnection(), userId);
            Exercise exercise = ExerciseDao.findById(DbUtil.getConnection(), exerciseId);
            newSolution = SolutionDao.create(DbUtil.getConnection(),description,exercise,user);

            List<Solution> recent = SolutionDao.findRecent(DbUtil.getConnection(), Integer.parseInt(numberSolutions));
            solutions.addAll(recent);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("solutions", solutions);
        request.getRequestDispatcher("solutionList.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Exercise> exercises = new ArrayList<>();
        List<User> users = new ArrayList<>();

        try {
            users = UserDao.loadAll(DbUtil.getConnection());
            exercises = ExerciseDao.loadAll(DbUtil.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("exercises", exercises);
        request.setAttribute("users", users);

        request.getRequestDispatcher("solutionAdd.jsp").forward(request, response);

    }



}
