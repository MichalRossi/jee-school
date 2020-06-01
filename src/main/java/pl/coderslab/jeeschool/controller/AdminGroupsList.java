package pl.coderslab.jeeschool.controller;

import pl.coderslab.jeeschool.dao.UserGroupDao;
import pl.coderslab.jeeschool.model.User;
import pl.coderslab.jeeschool.model.UserGroup;
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

@WebServlet("/AdminGroupsList")
public class AdminGroupsList extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserGroup> userGroups = new ArrayList<>();
        try {
            userGroups = UserGroupDao.loadAllGroups(DbUtil.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("userGroups", userGroups);
        request.getRequestDispatcher("adminGroupsList.jsp").forward(request, response);
    }

}
