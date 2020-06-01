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

@WebServlet("/GroupAdd")
public class GroupAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String groupName = request.getParameter("groupName");
        List<UserGroup> userGroups = new ArrayList<>();
        try  {

            if(groupName!=null){
                userGroups.add(UserGroupDao.create(DbUtil.getConnection(),groupName));
            }
            userGroups = UserGroupDao.loadAllGroups(DbUtil.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("userGroups",userGroups);
        request.getRequestDispatcher("groupAdd.jsp").forward(request, response);

    }

}
