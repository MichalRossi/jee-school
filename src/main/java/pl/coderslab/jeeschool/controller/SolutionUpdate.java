package pl.coderslab.jeeschool.controller;

import pl.coderslab.jeeschool.dao.SolutionDao;
import pl.coderslab.jeeschool.model.Solution;
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

@WebServlet("/SolutionUpdate")
public class SolutionUpdate extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String description = request.getParameter("description");
        int solutionId = Integer.parseInt(request.getParameter("solutionId"));

        String numberSolutions = getServletContext().getInitParameter("number-solutions");
        List<Solution> solutions = new ArrayList<>();

        Solution solutionUpdated = new Solution();

        try {
            solutionUpdated = SolutionDao.getSolutionDetails(DbUtil.getConnection(), solutionId);
            SolutionDao.update(DbUtil.getConnection(), solutionUpdated, description);
            List<Solution> recent = SolutionDao.findRecent(DbUtil.getConnection(), Integer.parseInt(numberSolutions));
            solutions.addAll(recent);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        request.setAttribute("solutions", solutions);
        request.getRequestDispatcher("solutionList.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int solutionId = Integer.parseInt(request.getParameter("id"));
        Solution solution = new Solution();
        try {
            solution = SolutionDao.getSolutionDetails(DbUtil.getConnection(), solutionId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("solution", solution);
        request.getRequestDispatcher("solutionUpdate.jsp").forward(request, response);
    }

}
