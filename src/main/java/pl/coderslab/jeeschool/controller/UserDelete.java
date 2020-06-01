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

@WebServlet("/UserDelete")
public class UserDelete extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("id"));
        List<User> users = new ArrayList<>();

        try  {
            users = UserDao.loadAll(DbUtil.getConnection());
            for(User element : users){
                if(element.getId() == userId){
                    User deletedUser = UserDao.findById(DbUtil.getConnection(),userId);
                    UserDao.delete(DbUtil.getConnection(),deletedUser);
                    users = UserDao.loadAll(DbUtil.getConnection());
                    request.setAttribute("users",users);
                    request.getRequestDispatcher("adminUsersList.jsp").forward(request, response);
                    return;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
