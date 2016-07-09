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
import jp.nobu.util.Validation;

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

	private void putErrorMessage(HttpServletRequest request, String key, String message) {
		request.setAttribute(key + "ErrorMsg", message);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("register") != null) {
			String id = request.getParameter("id");

			// 入力チェック
			boolean hasError = false;
			if (Validation.isBlank(id))
				putErrorMessage(request, "id", "入力してください。");

			if (hasError == false) {
				UserService.INSTANCE.registerUserInfo(id, request.getParameter("pass"), request.getParameter("name"));
			}

			getServletContext().getRequestDispatcher("/register.jsp");

		}
		if (request.getParameter("delete") != null) {
			String id = request.getParameter("user_id");

			// 入力チェック
			boolean hasError = false;
			if (Validation.isBlank(id))
				putErrorMessage(request, "id", "入力してください。");

			if (hasError == false) {
				UserService.INSTANCE.deleteUserByUserId(request.getParameter("user_id"));
				List<User> users = UserService.INSTANCE.getUsers();
				request.setAttribute("users", users);
			}
			getServletContext().getRequestDispatcher("/userList.jsp").forward(request, response);
			// 存在する場合の処理 成功画面に遷移
		} else {
			String id = request.getParameter("user_id");

			// 入力チェック
			boolean hasError = false;
			if (Validation.isBlank(id))
				putErrorMessage(request, "id", "入力してください。");

			if (hasError == false) {
				UserService.INSTANCE.updateUserName(request.getParameter("user_id"), request.getParameter("name"));
				List<User> users = UserService.INSTANCE.getUsers();
				request.setAttribute("users", users);
			}
			getServletContext().getRequestDispatcher("/userList.jsp").forward(request, response);
		}

	}
}
