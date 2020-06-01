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

@WebServlet("/panelAdmin")
public class Admin extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("panelAdmin.jsp").forward(request, response);
    }

}
