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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/SolutionDetails")
public class SolutionDetails extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int solutionId = Integer.parseInt(request.getParameter("id"));
        Solution solution = new Solution();
        try {
            solution = SolutionDao.getSolutionDetails(DbUtil.getConnection(),solutionId);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        request.setAttribute("solution", solution);
        request.getRequestDispatcher("solutionDetails.jsp").forward(request, response);
    }

}
