package pl.coderslab.jeeschool.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.coderslab.jeeschool.dao.SolutionDao;
import pl.coderslab.jeeschool.model.Solution;
import pl.coderslab.jeeschool.util.DbUtil;

@WebServlet("/")
public class HomeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String numberSolutions = getServletContext().getInitParameter("number-solutions");
        List<Solution> solutions = new ArrayList<>();

        try {
            solutions.addAll(SolutionDao.findRecent(DbUtil.getConnection(), Integer.parseInt(numberSolutions)));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("solutions", solutions);
        request.getRequestDispatcher("solutionList.jsp").forward(request, response);
    }

}
