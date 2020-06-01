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

import java.util.List;
import java.util.ArrayList;

@WebServlet("/SolutionDelete")
public class SolutionDelete extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int solutionId = Integer.parseInt(request.getParameter("id"));
        String numberSolutions = getServletContext().getInitParameter("number-solutions");
        List<Solution> solutions = new ArrayList<>();
        Solution solutionDeleted = new Solution();

        try {
            solutionDeleted = SolutionDao.getSolutionDetails(DbUtil.getConnection(), solutionId);
            SolutionDao.delete(DbUtil.getConnection(), solutionDeleted);
            List<Solution> recent = SolutionDao.findRecent(DbUtil.getConnection(), Integer.parseInt(numberSolutions));
            solutions.addAll(recent);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        request.setAttribute("solutions", solutions);
        request.getRequestDispatcher("solutionList.jsp").forward(request, response);
    }

}
