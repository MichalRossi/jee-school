package pl.coderslab.jeeschool.controller;

import pl.coderslab.jeeschool.dao.UserDao;
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

@WebServlet("/Users")
public class Users extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<User> users = new ArrayList<>();

        try {
            users = UserDao.loadAllByGroupId(DbUtil.getConnection(),id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("users", users);
        request.getRequestDispatcher("users.jsp").forward(request, response);
    }

}
