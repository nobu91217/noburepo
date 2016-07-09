package jp.nobu.User;

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
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<User> users = UserService.INSTANCE.getUsers();
		request.setAttribute("users", users);
		getServletContext().getRequestDispatcher("/userList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("register") != null) {
			UserService.INSTANCE.registerUserInfo(request.getParameter("id"), request.getParameter("pass"),
					request.getParameter("name"));
			
			getServletContext().getRequestDispatcher("/register.jsp");
			
		}
		if (request.getParameter("delete") != null) {
			UserService.INSTANCE.deleteUserByUserId(request.getParameter("user_id"));
			List<User> users = UserService.INSTANCE.getUsers();
			request.setAttribute("users", users);
			getServletContext().getRequestDispatcher("/userList.jsp").forward(request, response);
			// 存在する場合の処理 成功画面に遷移
		} else {
			UserService.INSTANCE.updateUserName(request.getParameter("user_id"), request.getParameter("name"));
			List<User> users = UserService.INSTANCE.getUsers();
			request.setAttribute("users", users);
			getServletContext().getRequestDispatcher("/userList.jsp").forward(request, response);
		}

	}
}
