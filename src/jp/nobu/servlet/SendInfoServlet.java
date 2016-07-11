package jp.nobu.servlet;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.nobu.service.UserService;

/**
 * Servlet implementation class SendServlet
 */
@WebServlet("/SendServlet")
public class SendInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		
			if(UserService.INSTANCE.sendInfo(request.getParameter("id"))) {
				request.setAttribute("id", rs.getString("user_id"));
				request.setAttribute("name", rs.getString("name"));
				getServletContext().getRequestDispatcher("/edit.jsp").forward(request, response);
			}
	}
}
