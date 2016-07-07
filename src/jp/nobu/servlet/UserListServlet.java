package jp.nobu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.nobu.domain.User;
import jp.nobu.service.UserService;

/**
 * Servlet implementation class UserListServlet
 */
@WebServlet("/UserList")
public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		List<User> users = UserService.INSTANCE.getUsers();
		request.setAttribute("users", users);
		getServletContext().getRequestDispatcher("/userList.jsp").forward(request, response);
		
	}


}
