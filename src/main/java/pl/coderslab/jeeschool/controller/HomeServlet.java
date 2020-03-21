package pl.coderslab.jeeschool.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.coderslab.jeeschool.dao.SolutionDao;
import pl.coderslab.jeeschool.model.Solution;

@WebServlet("/")
public class HomeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String numberSolutions = getServletContext().getInitParameter("number-solutions");
        List<Solution> lastItems = SolutionDao.findLastItems(Integer.parseInt(numberSolutions));


        request.setAttribute("solutions", lastItems);
        request.getRequestDispatcher("solutionList.jsp").forward(request, response);
    }

}
