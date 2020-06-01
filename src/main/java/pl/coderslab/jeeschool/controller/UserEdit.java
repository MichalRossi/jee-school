package pl.coderslab.jeeschool.controller;

import pl.coderslab.jeeschool.dao.UserDao;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/UserEdit")
public class UserEdit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String userName = request.getParameter("userName");
        String userEmail = request.getParameter("userEmail");
        String userPassword = request.getParameter("userPassword");
        String userGroupName = request.getParameter("userGroup");
        List<User> users = new ArrayList<>();

        try  {

            users = UserDao.loadAll(DbUtil.getConnection());
            for(User element : users){
                if(element.getId() == userId){
                    User updatedUser = UserDao.findById(DbUtil.getConnection(),userId);
                    UserGroup updateUserGroup = UserGroupDao.findByName(DbUtil.getConnection(),userGroupName);
                    UserDao.update(DbUtil.getConnection(),updatedUser, userName, userEmail, userPassword, updateUserGroup);
                    List<User> updatedUsers = UserDao.loadAll(DbUtil.getConnection());
                    request.setAttribute("users",updatedUsers);
                    request.getRequestDispatcher("adminUsersList.jsp").forward(request, response);
                    return;
                }
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            int userId = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("id",userId);
            request.getRequestDispatcher("userEdit.jsp").forward(request, response);

    }

}
