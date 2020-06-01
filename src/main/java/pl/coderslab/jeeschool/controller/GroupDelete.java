package pl.coderslab.jeeschool.controller;

import pl.coderslab.jeeschool.dao.UserGroupDao;
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

@WebServlet("/GroupDelete")
public class GroupDelete extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int groupId = Integer.parseInt(request.getParameter("id"));
        List<UserGroup> userGroups = new ArrayList<>();
        try  {
            userGroups = UserGroupDao.loadAllGroups(DbUtil.getConnection());
            for(UserGroup element : userGroups){
                if(element.getId() == groupId){
                    UserGroup deletedGroup = UserGroupDao.findById(DbUtil.getConnection(),groupId);
                    UserGroupDao.delete(DbUtil.getConnection(),deletedGroup);
                    userGroups = UserGroupDao.loadAllGroups(DbUtil.getConnection());
                    request.setAttribute("userGroups",userGroups);
                    request.getRequestDispatcher("adminGroupsList.jsp").forward(request, response);
                    return;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
