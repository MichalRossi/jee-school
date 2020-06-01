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

@WebServlet("/UserAdd")
public class UserAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String userEmail = request.getParameter("userEmail");
        String userPassword = request.getParameter("userPassword");
        String userGroupName = request.getParameter("userGroup");
        List<User> users = new ArrayList<>();
        boolean groupExist = true;
        try  {
            groupExist = groupExist(userGroupName);

            if(userName!=null && userEmail!=null && userGroupName!=null && userPassword!=null ){
                if(groupExist) {
                    User newUser = UserDao.create(DbUtil.getConnection(), userName, userEmail, userPassword, userGroupName);
                    users.add(newUser);
                }

            }

            users = UserDao.loadAll(DbUtil.getConnection());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("groupExist",groupExist);
        request.setAttribute("users",users);
        request.getRequestDispatcher("userAdd.jsp").forward(request, response);

    }

    private boolean groupExist(String groupName) throws SQLException {
        List<UserGroup> groups = UserGroupDao.loadAllGroups(DbUtil.getConnection());

        for(UserGroup item : groups){
            if(item.getName().equals(groupName)){
                return true;
            }
        }
        return false;
    }

}
